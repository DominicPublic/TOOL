# Network-Layer Protocol Comparison

| Schema | Primary role | Strengths | Trade-offs and fit |
|---|---|---|---|
| [IPv4](IPv4.json) | Addressing and packet delivery | Nearly universal deployment and broad compatibility | Limited address space; commonly requires address translation and careful subnet management. |
| [IPv6](IPv6.json) | Addressing and packet delivery | Vast address space and standardized autoconfiguration | Requires dual-stack planning or translation during migration; operational practices differ from IPv4. |
| [ICMPv4](ICMPv4.json) | IPv4 errors and diagnostics | Essential for path diagnostics and network feedback | Filtering all ICMP can break path-MTU discovery; rate-limit and selectively permit messages. |
| [ICMPv6](ICMPv6.json) | IPv6 errors, diagnostics, and neighbor discovery | Core to IPv6 operation, including neighbor discovery and path-MTU behavior | Must preserve required control messages; blanket blocking can break IPv6 connectivity. |

IPv4 and IPv6 provide addressing and packet delivery; ICMPv4 and ICMPv6 carry related control and diagnostic messages. ICMPv6 is especially integral to normal IPv6 link operation.