# Hashing Protocol Comparison

General-purpose hashes are fast and are suitable for integrity identifiers, signatures, and content addressing. Password hashing is deliberately expensive and should be used for password storage. These groups are not substitutes for one another.

| Schema | Family and output | Strengths | Trade-offs and fit |
|---|---|---|---|
| [Argon2id](Argon2id.json) | Memory-hard password hashing | Recommended modern password-hashing choice; balances side-channel and GPU resistance | Requires tuning memory, iterations, and parallelism for the deployment environment. |
| [bcrypt](bcrypt.json) | Adaptive password hashing | Mature and widely available | Has a 72-byte input limit and less memory hardness than Argon2id or scrypt. |
| [PBKDF2](PBKDF2.json) | Iterated password-based derivation | Very broad library and compliance support | Primarily CPU-hard, so GPUs can evaluate guesses efficiently; iteration count must be kept current. |
| [scrypt](scrypt.json) | Memory-hard password hashing | Raises the cost of large-scale parallel guessing | Requires memory-aware tuning and careful parameter validation. |
| [BLAKE2b](BLAKE2b.json) | General-purpose hash, up to 512-bit digest | Fast on 64-bit systems; supports configurable digest length and keyed mode | Not a password hash; both parties must agree on digest size and optional key use. |
| [BLAKE2s](BLAKE2s.json) | General-purpose hash, up to 256-bit digest | Suited to smaller or 32-bit platforms | Not a password hash; smaller maximum output than BLAKE2b. |
| [BLAKE3](BLAKE3.json) | Tree hash and extendable output | High throughput, parallel processing, and flexible output length | Less universally standardized in older ecosystems; not a password hash. |
| [MD5](MD5.json) | Legacy 128-bit hash | Useful only for non-security legacy checksums | Collision-broken; never use for signatures, adversarial integrity, or password storage. |
| [SHA-1](SHA-1.json) | Legacy 160-bit hash | Compatibility with old formats | Collision-broken for security use; migrate to SHA-2 or SHA-3. |
| [SHA-224](SHA-224.json) | SHA-2, 224-bit digest | Compact SHA-2 output with wide library availability | Less common than SHA-256; not a password hash. |
| [SHA-256](SHA-256.json) | SHA-2, 256-bit digest | Broad interoperability and general-purpose security support | Fixed output; fast hashing makes it unsuitable for password storage. |
| [SHA-384](SHA-384.json) | SHA-2, 384-bit digest | Higher digest strength with strong platform support | Larger output and often more processing than SHA-256; not a password hash. |
| [SHA-512](SHA-512.json) | SHA-2, 512-bit digest | High security margin and good performance on 64-bit systems | Larger output; not a password hash. |
| [SHA-512/256](SHA-512-256.json) | SHA-2, 256-bit variant | 256-bit output with SHA-512-family processing characteristics | Less common than SHA-256; not simply a truncated SHA-512 digest and not a password hash. |
| [SHA3-224](SHA3-224.json) | SHA-3, 224-bit digest | Keccak-based alternative to SHA-2 | Less hardware acceleration and ecosystem support in some environments. |
| [SHA3-256](SHA3-256.json) | SHA-3, 256-bit digest | Standardized construction distinct from SHA-2 | Interoperability and performance may differ from SHA-256 deployments. |
| [SHA3-384](SHA3-384.json) | SHA-3, 384-bit digest | Stronger fixed-length SHA-3 option | Larger output and may be slower on platforms optimized for SHA-2. |
| [SHA3-512](SHA3-512.json) | SHA-3, 512-bit digest | Maximum fixed digest size in the SHA-3 family | Larger output; not a password hash. |
| [SHAKE128](SHAKE128.json) | SHA-3 extendable-output function | Caller selects output length; useful for XOF-based constructions | Output length is part of the protocol and must be agreed by all parties. |
| [SHAKE256](SHAKE256.json) | SHA-3 extendable-output function | Higher security strength than SHAKE128 with flexible output | Output length is part of the protocol; may cost more than SHAKE128. |

## Quick Selection

| Need | Typical choice |
|---|---|
| Store passwords | Argon2id; use scrypt or bcrypt when compatibility dictates, and PBKDF2 where required by platform or policy |
| General-purpose 256-bit digest | SHA-256 for broad compatibility; SHA3-256, BLAKE2, or BLAKE3 where ecosystem support fits |
| Variable-length digest | SHAKE128, SHAKE256, or BLAKE3 with an explicitly agreed output length |
| Legacy verification only | MD5 or SHA-1 only when reproducing non-security formats; do not rely on them against an attacker |