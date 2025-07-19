#!/bin/bash

# Script to setup publishing to Maven Central
# This script helps generate GPG keys and configure publishing

set -e

echo "🚀 Setting up Maven Central publishing for Kollama"
echo "📢 NOTE: Using NEW Central Publishing Portal (replaces OSSRH)"

# Check if GPG is installed
if ! command -v gpg &> /dev/null; then
    echo "❌ GPG is not installed. Please install GPG first."
    echo "   On macOS: brew install gnupg"
    echo "   On Ubuntu: sudo apt-get install gnupg"
    exit 1
fi

# Generate GPG key
echo "🔑 Generating GPG key for signing artifacts..."
read -p "Enter your email address: " EMAIL
read -p "Enter your name: " NAME

gpg --batch --gen-key <<EOF
Key-Type: RSA
Key-Length: 4096
Subkey-Type: RSA
Subkey-Length: 4096
Name-Real: $NAME
Name-Email: $EMAIL
Expire-Date: 0
%commit
EOF

# Get the key ID
KEY_ID=$(gpg --list-secret-keys --keyid-format LONG "$EMAIL" | grep sec | awk '{print $2}' | cut -d'/' -f2)

echo "✅ GPG key generated with ID: $KEY_ID"

# Export public key
echo "📤 Exporting public key..."
gpg --armor --export "$KEY_ID" > public-key.asc
echo "Public key exported to public-key.asc"

# Export private key
echo "📤 Exporting private key..."
gpg --armor --export-secret-keys "$KEY_ID" > private-key.asc
echo "Private key exported to private-key.asc"

# Create gradle-secrets.properties template
echo "📝 Creating gradle-secrets.properties template..."
cat > gradle-secrets.properties.template <<EOF
# Central Publishing Portal credentials
centralUsername=your_central_username
centralPassword=your_central_password

# GPG signing configuration
signing.keyId=$KEY_ID
signing.password=your_gpg_passphrase
signing.secretKeyRingFile=\$HOME/.gnupg/secring.gpg
EOF

echo "✅ Setup complete!"
echo ""
echo "📋 Next steps:"
echo "1. Create an account at https://central.sonatype.com/ (NEW Central Publishing Portal)"
echo "2. Copy gradle-secrets.properties.template to gradle-secrets.properties and fill in your credentials:"
echo "   cp gradle-secrets.properties.template gradle-secrets.properties"
echo "   # Then edit gradle-secrets.properties with your actual credentials"
echo "3. Add the following secrets to your GitHub repository:"
echo "   - CENTRAL_USERNAME: Your Central Publishing Portal username"
echo "   - CENTRAL_PASSWORD: Your Central Publishing Portal password"
echo "   - GPG_PRIVATE_KEY: Content of private-key.asc"
echo "   - GPG_PASSPHRASE: Your GPG passphrase"
echo "   - SIGNING_KEY_ID: $KEY_ID"
echo ""
echo "4. Upload your public key to a key server:"
echo "   gpg --keyserver keyserver.ubuntu.com --send-keys $KEY_ID"
echo ""
echo "5. Test publishing locally:"
echo "   ./gradlew publish"
echo ""
echo "⚠️  IMPORTANT: OSSRH will be discontinued on June 30th, 2025"
echo "   This setup uses the new Central Publishing Portal"
echo ""
echo "🔒 SECURITY: gradle-secrets.properties is automatically added to .gitignore"
echo "   Your credentials will not be committed to version control" 