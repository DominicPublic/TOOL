# Application Protocol Comparison

| Schema | Typical use | Strengths | Trade-offs and fit |
|---|---|---|---|
| [AMQP](AMQP.json) | Enterprise message brokers | Rich routing, acknowledgments, and brokered delivery | More operational and protocol complexity than lightweight telemetry protocols. |
| [CoAP](CoAP.json) | Constrained devices and IoT | Compact, REST-like messaging with constrained-network options | Requires careful DTLS, OSCORE, or TLS configuration and has a smaller general-purpose ecosystem. |
| [DHCPv4](DHCPv4.json) | IPv4 address and network configuration | Automated lease and option assignment | Local network service; protect against rogue servers and coordinate pools with static assignments. |
| [DHCPv6](DHCPv6.json) | IPv6 address configuration and prefix delegation | Supports stateful configuration and delegated prefixes | Coexists with IPv6 SLAAC and router advertisements; deployment policy must define their roles. |
| [DNS](DNS.json) | Name resolution | Fundamental, ubiquitous service with UDP and TCP support | Plain DNS does not encrypt queries; resolver trust and DNSSEC validation are separate concerns. |
| [DNS-over-HTTPS](DNS-over-HTTPS.json) | Encrypted DNS over HTTPS | Uses HTTPS infrastructure and can traverse common networks | Can complicate enterprise resolver policy and observability; TLS protects the channel, not the truth of answers by itself. |
| [DNS-over-TLS](DNS-over-TLS.json) | Encrypted DNS on a dedicated TLS port | Separates DNS traffic from ordinary web traffic and protects the resolver channel | Port 853 can be blocked; certificate and resolver identity must be verified. |
| [FTP](FTP.json) | Legacy file transfer over explicit TLS | Retains FTP ecosystem compatibility while protecting credentials and data | Multiple data connections and firewall/NAT configuration are cumbersome; prefer SFTP for new deployments. |
| [HTTP-1.1](HTTP-1.1.json) | General web request/response | Universal compatibility and simple deployment | Text-based framing and limited multiplexing compared with newer versions; use TLS in production. |
| [HTTP-2](HTTP-2.json) | Multiplexed web transport | Binary framing, header compression, and concurrent streams over a connection | Common deployments use TLS; TCP-level packet loss can delay all streams on the connection. |
| [HTTP-3](HTTP-3.json) | Web transport over QUIC | Multiplexed streams without TCP-level head-of-line blocking; TLS 1.3 integrated | Requires QUIC/UDP support and newer infrastructure. |
| [IMAP](IMAP.json) | Server-managed email access | Supports folders, flags, and synchronized mailbox state | More state and server interaction than POP3; use TLS and modern authentication. |
| [MQTT](MQTT.json) | Lightweight publish/subscribe messaging | Small protocol footprint, broker-based fan-out, and QoS levels | Broker availability, topic authorization, and retained-message policy need attention; use TLS. |
| [NTP](NTP.json) | Clock synchronization | Widely implemented and efficient | Time integrity is security-sensitive; use trusted sources and Network Time Security where available. |
| [POP3](POP3.json) | Simple email retrieval | Straightforward download-oriented mailbox access | Limited synchronization and folder semantics; IMAP is usually better for multiple devices. |
| [SFTP](SFTP.json) | Secure file transfer over SSH | Single encrypted SSH connection with host-key verification | Requires SSH account/key lifecycle management; it is not FTP with TLS. |
| [SMTP](SMTP.json) | Email submission and relay | Universal email transport and broad infrastructure support | Opportunistic TLS is not enough for sensitive submission; require TLS and authenticate appropriately. |
| [SNMPv3](SNMPv3.json) | Network monitoring and management | Authentication and privacy protections absent from older SNMP versions | User, authentication, and privacy settings must align; avoid SNMPv1/v2c for sensitive management. |
| [SSH](SSH.json) | Secure remote shell and tunneling | Encrypted remote administration and key-based authentication | Host-key verification and key access controls are essential; exposed administrative access needs hardening. |
| [Syslog](Syslog.json) | Log forwarding | Simple, widely supported event transport | UDP can lose messages; use TLS transport for confidentiality and peer authentication when needed. |
| [WebSocket](WebSocket.json) | Full-duplex browser/server messaging | Persistent bidirectional channel using web infrastructure | Connection lifetime, authorization, origin checks, and backpressure require application-level handling. |

## Quick Selection

| Need | Typical choice |
|---|---|
| Web APIs and pages | HTTP/2 or HTTP/3 when supported; retain HTTP/1.1 for compatibility |
| Encrypted name resolution | DNS-over-HTTPS or DNS-over-TLS, according to network policy |
| Brokered telemetry | MQTT for lightweight pub/sub; AMQP for richer broker workflows |
| Secure file transfer | SFTP for new integrations; explicit-TLS FTP only for compatibility |
| Email clients | IMAP for synchronized mailboxes; POP3 only for simple download workflows |
| Remote administration | SSH with strict host-key verification |