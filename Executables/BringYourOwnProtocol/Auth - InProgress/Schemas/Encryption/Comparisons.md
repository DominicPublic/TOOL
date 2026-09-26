# Encryption Protocol Comparison

This catalog mixes encryption algorithms, key wrapping, hybrid encryption, and a transport protocol. They solve different problems and are not interchangeable; use authenticated encryption for data and a protocol such as TLS for transport.

| Schema | Primitive or role | Strengths | Trade-offs and fit |
|---|---|---|---|
| [AES-CBC](AES-CBC.json) | Block cipher mode | Broad legacy compatibility | Does not authenticate ciphertext; this schema requires independent encrypt-then-MAC. Prefer AEAD for new designs. |
| [AES-CCM](AES-CCM.json) | Authenticated block cipher mode | Standard AEAD used in constrained and wireless environments | Nonce and tag sizes have constraints; implementation throughput can be lower than GCM on some platforms. |
| [AES-GCM-SIV](AES-GCM-SIV.json) | Misuse-resistant AEAD | Safer than ordinary GCM under accidental nonce reuse | Nonce reuse is still undesirable; support and performance vary by library. |
| [AES-KW](AES-KW.json) | Key wrapping | Purpose-built integrity-protected wrapping for cryptographic keys | Wraps keys, not arbitrary-length application data; use the matching unwrap operation. |
| [ChaCha20-Poly1305](ChaCha20-Poly1305.json) | Authenticated stream cipher | Fast in software and widely supported | Requires unique nonces per key; standard nonce length is 12 bytes. |
| [HPKE](HPKE.json) | Hybrid public-key encryption construction | Combines public-key encapsulation, key derivation, and AEAD for recipient encryption | Requires compatible suite selection and recipient key management; not a transport session protocol. |
| [RSA-OAEP](RSA-OAEP.json) | Asymmetric encryption padding | Interoperable public-key encryption for small secrets and key material | Ciphertext is size-limited and expensive; normally use it to protect a symmetric key, not bulk data. |
| [TLS-1.3](TLS-1.3.json) | Transport security protocol | Provides authenticated, encrypted connections with standardized negotiation | Protects data in transit, not stored data; certificate validation and endpoint configuration remain essential. |
| [XChaCha20-Poly1305](XChaCha20-Poly1305.json) | Extended-nonce AEAD | 24-byte nonce simplifies safe random nonce generation in some applications | Not as universally standardized or hardware-accelerated as the 12-byte-nonce variant. |

## Quick Selection

| Need | Typical choice |
|---|---|
| Encrypt application records | AES-CCM, AES-GCM-SIV, or ChaCha20-Poly1305, based on platform support and nonce requirements |
| Encrypt to a recipient public key | HPKE; RSA-OAEP is mainly for interoperability and small key material |
| Protect cryptographic keys at rest | AES-KW |
| Secure a network connection | TLS 1.3 |
| Maintain an older CBC integration | AES-CBC only with the required independent encrypt-then-MAC construction |