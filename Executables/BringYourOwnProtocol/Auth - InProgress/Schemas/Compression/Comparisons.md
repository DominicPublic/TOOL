# Compression Algorithm and Protocol Comparison

Compression formats include codecs, stream wrappers, archive containers, and protocol-specific header compression. They are not interchangeable: match the format and options expected by the peer or archive reader.

| Schema | Family and role | Strengths | Trade-offs and fit |
|---|---|---|---|
| [7z](7z.json) | Multi-codec archive container | Supports solid archives and several high-ratio codecs | Solid archives can increase extraction cost and limit random access; extraction needs strict resource and path limits. |
| [Brotli](Brotli.json) | LZ-based stream compression | Strong ratios for web content; widely used for HTTP representations | Higher quality settings cost more CPU; window size affects memory use. |
| [GZIP](GZIP.json) | DEFLATE stream wrapper | Ubiquitous file and HTTP content encoding | Less efficient than newer codecs; do not confuse gzip framing with raw DEFLATE. |
| [DEFLATE](DEFLATE.json) | LZ77 with Huffman coding; raw, zlib, or gzip framing | Broad compatibility and extensive tooling | Often compresses less efficiently than newer codecs; framing must match the consumer. |
| [HPACK](HPACK.json) | HTTP/2 header compression | Standard compression for HTTP/2 header fields | Stateful dynamic tables require bounded, coordinated settings. |
| [Zstandard](Zstandard.json) | Fast general-purpose framed compression | Strong speed and ratio balance; supports dictionaries and checksums | Both endpoints need compatible Zstandard support; dictionary identity must be coordinated. |
| [LZ4](LZ4.json) | Fast LZ-based block and frame compression | Very high decompression and compression throughput | Typically prioritizes speed over maximum compression ratio. |
| [LZMA](LZMA.json) | Dictionary-based compression codec | High ratio and broad archive-tool support | Can require substantial memory and CPU. |
| [Snappy](Snappy.json) | Fast block or framed compression | Low overhead and broad use in data systems | Modest compression ratio and few tuning options. |
| [LZMA2](LZMA2.json) | Dictionary-based compression stream | High compression ratio with configurable memory and encoder effort | Can require substantial memory and CPU, especially at higher presets. |
| [LZO](LZO.json) | Low-latency LZ-family codec | Fast compression and decompression | Lower ratio than slower general-purpose codecs; variants must match. |
| [PPMd](PPMd.json) | Context-modeling codec | Effective on text and structured data | Memory use and compatibility depend on variant and model parameters. |
| [QPACK](QPACK.json) | HTTP/3 header compression | Designed to compress HTTP headers over QUIC streams | Table capacity and blocked-stream limits affect memory and decoding behavior. |
| [bzip2](bzip2.json) | Block-sorting compression stream | Useful for compatibility with existing archives and tools | Generally slower and less suitable for low-latency workloads than modern alternatives. |
| [XZ](XZ.json) | Stream container commonly using LZMA2 | High compression ratio and integrity check options | Decoder memory usage can be high; configure limits for untrusted input. |
| [ZLIB](ZLIB.json) | DEFLATE stream wrapper | Common interoperable framing with optional preset dictionaries | Requires agreement on dictionary and wrapper; not the same wire format as gzip. |
| [ZIP](ZIP.json) | Multi-entry archive container | Broad desktop and platform compatibility | Entry paths and expansion sizes must be validated when extracting untrusted archives. |

## Quick Selection

| Need | Typical choice |
|---|---|
| Web content | Brotli or gzip-framed DEFLATE, based on client support |
| HTTP/2 header compression | HPACK |
| HTTP/3 header compression | QPACK |
| General-purpose application data | Zstandard |
| Low-latency compression | LZ4 or Snappy |
| High compression ratio for offline data | LZMA2 |
| Archive interoperability | ZIP; use 7z when its codec support and extraction characteristics fit |
| Legacy stream compatibility | bzip2, XZ, or the required DEFLATE framing |

Compression does not provide confidentiality or integrity. When decompressing untrusted input, enforce output-size, expansion-ratio, memory, processing-time, and archive-path limits regardless of codec or container.