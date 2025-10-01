# Algorithms Pack (Java)

This repository contains **from-scratch implementations** of several core algorithms developed as part of Auburn CPSC 3280 coursework. Each algorithm was written manually, without external libraries, to demonstrate understanding of problem-solving and algorithm design in Java.

## Purpose
- Show the ability to translate algorithmic theory into working Java code.
- Practice clean design: modular structure, testability, and readability.
- Demonstrate range across recursion, greedy methods, graph algorithms, and trees.
- Serve as a portfolio-ready DS&A reference for interviews and technical discussions.

## Contents

### Binary Search Tree (BST)
- **Problem:** Efficiently store and query a dynamic set of integers. Support insertion, membership testing, traversals, and measuring height.
- **Algorithm:** Implements a binary search tree where each node has up to two children.
    - Insert: recursively place nodes based on ordering.
    - Search: recursively or iteratively follow branches.
    - Traversals: inorder (sorted), preorder, and postorder.
    - Height: max depth of the tree.
- **Complexity:** Average `O(log n)` for insert/search, worst `O(n)` (degenerate tree).

### Dijkstra’s Shortest Path
- **Problem:** Given a weighted graph and a source node, compute the shortest distance from the source to all other vertices.
- **Algorithm:** Dijkstra’s algorithm using a priority queue (min-heap).
    - Initialize distances as infinity, set source = 0.
    - Relax edges repeatedly, always choosing the current minimum distance vertex.
    - Stop when all vertices are visited.
- **Complexity:** `O((V+E) log V)` with a binary heap.
- **Input format:** text file where each line is `u v w` (edge from `u` to `v` with weight `w`). Lines starting with `#` are comments.

### Activity Selection (Greedy + Recursive)
- **Problem:** Given start and finish times of activities, select the maximum number of non-overlapping activities.
- **Algorithm:**
    - **Greedy:** Always pick the activity that finishes earliest, then discard overlapping ones.
    - **Recursive:** Explore subsets by including or excluding activities.
- **Complexity:** Greedy = `O(n log n)` (after sorting by finish time), Recursive = exponential but demonstrates exhaustive exploration.

---

## Requirements
- JDK 22 (or JDK 17+)

## Notes
- All implementations were written from scratch.
- No external algorithm libraries were used.
- Each directory contains a simple Main driver and sample input (for Dijkstra) to demonstrate usage.

---

## License
MIT License – see [LICENSE](LICENSE) for details.
