# Signature Protocol Comparison

This folder contains both signature algorithms and signature container formats. JWS and XMLDSig describe how signatures and related metadata are represented; they still depend on a selected signing algorithm and key.

| Schema | Type | Strengths | Trade-offs and fit |
|---|---|---|---|
| [DSA](DSA.json) | Signature algorithm | Useful for older interoperability | Legacy and unsuitable for new deployments; prefer EdDSA, ECDSA, or RSA-PSS. |
| [ECDSA](ECDSA.json) | Elliptic-curve signature algorithm | Compact keys and signatures with broad support | Secure nonce generation is critical; curve and hash must be selected consistently. |
| [Ed25519](Ed25519.json) | Edwards-curve signature algorithm | Fast, compact, deterministic signing and simple key handling | Not available in every legacy or regulated environment. |
| [Ed448](Ed448.json) | Edwards-curve signature algorithm | Larger security margin than Ed25519 while retaining deterministic signing | Larger and less widely supported than Ed25519. |
| [JWS](JWS.json) | JSON signature format | Standardized signing envelope for JSON and tokens; supports multiple algorithm families | The `alg` and key policy must be validated by the application; the format does not make an unsafe algorithm safe. |
| [ML-DSA](ML-DSA.json) | Post-quantum signature algorithm | Standardized lattice-based signatures designed to resist quantum attacks | Keys and signatures are substantially larger than classical signatures; library and interoperability support is newer. |
| [RSA-PKCS1-v1_5](RSA-PKCS1-v1_5.json) | Legacy RSA signature padding | Very broad compatibility | Older deterministic padding; prefer RSA-PSS for new systems. |
| [RSA-PSS](RSA-PSS.json) | RSA signature padding | Modern randomized RSA signature scheme with strong standards support | Larger keys and signatures than EdDSA/ECDSA; parameters must match during verification. |
| [XMLDSig](XMLDSig.json) | XML signature format | Integrates signatures with XML documents and enterprise federation systems | Canonicalization and reference handling are complex; wrapping and validation errors are common risks. |

## Quick Selection

| Need | Typical choice |
|---|---|
| Modern compact classical signatures | Ed25519, where supported |
| Classical signatures with broad enterprise compatibility | ECDSA or RSA-PSS |
| Post-quantum signatures | ML-DSA, after confirming ecosystem support and signature-size requirements |
| Signed JSON or token payloads | JWS with a carefully allowlisted algorithm and key policy |
| Signed XML documents | XMLDSig with strict reference, transform, and canonicalization validation |