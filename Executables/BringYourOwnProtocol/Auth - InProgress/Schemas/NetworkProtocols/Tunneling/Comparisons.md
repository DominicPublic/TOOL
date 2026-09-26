# Tunneling Protocol Comparison

| Schema | Tunnel model | Strengths | Trade-offs and fit |
|---|---|---|---|
| [IPsec](IPsec.json) | Network-layer security using IKEv2 and ESP | Mature standards, broad enterprise and gateway support, and flexible peer authentication | Suite negotiation and policy configuration can be complex; interoperability depends on matching proposals and identities. |
| [WireGuard](WireGuard.json) | Modern, key-based VPN tunnel | Small protocol surface, straightforward peer configuration, and efficient cryptography | Smaller built-in identity and policy model; public-key, endpoint, and allowed-IP lifecycle must be managed externally. |

Use IPsec when compatibility with existing gateways or standards-based enterprise integration is required. WireGuard is often simpler for managed point-to-point or mesh VPNs when its key and routing model fits.