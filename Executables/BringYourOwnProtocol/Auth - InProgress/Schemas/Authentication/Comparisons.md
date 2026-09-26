# Authentication Protocol Comparison

These schemas describe representative configurations, not complete wire-format specifications. OAuth 2.0 is included because it commonly accompanies sign-in, but OAuth 2.0 itself delegates authorization; use OIDC when an identity assertion is needed.

| Schema | Primary role | Strengths | Trade-offs and fit |
|---|---|---|---|
| [HTTP-Basic](HTTP-Basic.json) | HTTP username/password authentication | Very simple and widely interoperable | Credentials are reusable; use only over TLS. |
| [HTTP-Digest](HTTP-Digest.json) | HTTP challenge-response authentication | Avoids sending the password directly | Legacy and less robust than modern options; prefer TLS-based modern authentication. |
| [Kerberos-V5](Kerberos-V5.json) | Ticket-based network authentication | Mutual authentication and single sign-on without sending passwords to each service | Requires KDC/realm management, synchronized clocks, and service-principal administration. |
| [LDAP](LDAP.json) | Directory bind and identity lookup | Integrates authentication with directory users and groups | LDAP is a directory access protocol, not a federation protocol; protect binds with TLS. |
| [Mutual-TLS](Mutual-TLS.json) | Client authentication using certificates | Strong machine-to-machine identity and transport protection | Certificate issuance, rotation, revocation, and trust configuration require operational care. |
| [NTLM](NTLM.json) | Legacy Windows challenge-response | Compatibility with older Windows environments | Legacy protocol with relay and deployment risks; prefer Kerberos and disable NTLMv1. |
| [OAuth2](OAuth2.json) | Delegated authorization | Standardized scoped access and broad ecosystem support | Not an authentication protocol by itself; use OIDC for user identity and use PKCE for authorization-code clients. |
| [OIDC](OIDC.json) | Federated authentication and identity claims | Adds interoperable identity to OAuth 2.0; supports modern web and mobile sign-in | Requires issuer, client, redirect URI, and token validation configuration. |
| [RADIUS](RADIUS.json) | Centralized network access authentication, authorization, and accounting | Common for Wi-Fi, VPN, and network access control | Shared-secret and transport handling matter; prefer protected transports such as RadSec where supported. |
| [SAML2](SAML2.json) | Browser-based enterprise identity federation | Mature ecosystem for workforce single sign-on | XML metadata and assertion handling are more complex than OIDC; validate signatures and audience carefully. |
| [SCRAM](SCRAM.json) | Salted challenge-response password authentication | Avoids sending the password itself and supports channel binding | Requires client/server mechanism compatibility and secure verifier storage. |
| [TACACS-Plus](TACACS-Plus.json) | Centralized administration of network devices | Separates authentication, authorization, and accounting; useful for device command control | Primarily an infrastructure administration protocol; protect its shared secret and transport. |
| [WS-Federation](WS-Federation.json) | Federated web sign-in, often with older enterprise identity systems | Supports established enterprise federation deployments | Legacy compared with OIDC; has more protocol-specific configuration and XML token handling. |
| [WebAuthn](WebAuthn.json) | Public-key browser authentication and passkeys | Phishing-resistant; private keys remain with the authenticator | Requires relying-party origin/domain coordination and compatible authenticators. |

## Quick Selection

| Need | Typical choice |
|---|---|
| User sign-in for a modern application | OIDC, optionally with WebAuthn as an authenticator |
| Enterprise federation with an existing identity provider | OIDC or SAML 2.0; WS-Federation only when required for compatibility |
| Service-to-service identity | Mutual TLS or Kerberos, depending on the trust and infrastructure model |
| Wi-Fi, VPN, or network access control | RADIUS; TACACS+ is common for network-device administration |
| Legacy compatibility | Use HTTP Digest, NTLM, or WS-Federation only where necessary and with compensating controls |