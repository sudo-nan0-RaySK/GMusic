# GMusic
A music application that relies on different types of Graph data structures to operate and serve social networking purposes
ORM Graph Database and Splay Trees with there application in a Musical Social Networking App


ORM/Non-Sql Databases vs Sql databases:

Object relational mappers (ORM) are utilized to abstract database access into your preferred object oriented programming language. On the other hand, raw SQL statements are a more direct way of communicating with a database. Usually raw SQL statements are either embedded into your database access layer or stored in your database as a stored procedure. Each approach has its advantages and disadvantages.
	•	ORM
	•	Advantages
	•	Switching and upgrading databases are easier.
	•	Great for simple CRUD.
	•	In theory, one object can represent an entire entity and be passed through an entire application.
	•	SQL injection is usually taken care of by ORM frameworks.
	•	Database tables can be generated from objects.
	•	Objects can be generated from database tables.
	•	Easy to store in source control.
	•	Disadvantages
	•	Slower than SQL.
	•	More memory usage than SQL.
	•	Have to learn quirky bugs for the specific ORM one is utilizing.
	•	SQL Embedded Into DAL
	•	Advantages
	•	Faster and more expressive than ORM.
	•	Easy to store in source control.
	•	Disadvantages
	•	Slower than stored procedure due to not being compiled and having to send the raw SQL over the network.
	•	Manual intervention to prevent SQL injection is required.
	•	Stored Procedure
	•	Advantages
	•	Faster and more expressive than ORM.
	•	Faster than SQL embedded into the database access layer.
	•	Creates a distinct separation of database logic and application logic.


Graph databases:

In computing, a graph database is a database that uses graph structures for semantic queries with nodes, edges and properties to represent and store data. A key concept of the system is the graph (or edge or relationship), which directly relates data items in the store. The relationships allow data in the store to be linked together directly, and in many cases retrieved with one operation.
This contrasts with relational databases that, with the aid of relational database management systems, permit managing the data without imposing implementation aspects like physical record chains; for example, links between data are stored in the database itself at the logical level, and relational algebra operations (e.g. join) can be used to manipulate and return related data in the relevant logical format. The execution of relational queries is possible with the aid of the database management systems at the physical level (e.g. using indexes), which permits boosting performance without modifying the logical structure of the database.
Graph databases, by design, allow simple and fast retrieval[citation needed] of complex hierarchical structures that are difficult to model[according to whom?] in relational systems. Graph databases are similar to 1970s network model databases in that both represent general graphs, but network-model databases operate at a lower level of abstraction[1] and lack easy traversal over a chain of edges.[2]
The underlying storage mechanism of graph databases can vary. Some depend on a relational engine and “store” the graph data in a table (although a table is a logical element, therefore this approach imposes another level of abstraction between the graph database, the graph database management system and the physical devices where the data is actually stored). Others use a key-value store or document-oriented database for storage, making them inherently NoSQL structures. Most[according to whom?] graph databases based on non-relational storage engines also add the concept of tags or properties, which are essentially relationships having a pointer to another document. This allows data elements to be categorized for easy retrieval en masse
Compared with relational databases, graph databases are often faster for associative data sets[citation needed] and map more directly to the structure of object-oriented applications. They can scale more naturally[citation needed] to large data sets as they do not typically need costly join operations (here costly means when executed on databases with non-optimal designs at the logical and physical levels). As they depend less on a rigid schema, they are marketed as more suitable to manage ad hoc and changing data with evolving schemas. Conversely, relational database management systems are typically faster at performing the same operation on large numbers of data elements, permitting the manipulation of the data in its natural structure.
Graph databases are a powerful tool for graph-like queries. For example, computing the shortest path between two nodes in the graph. Other graph-like queries can be performed over a graph database in a natural way (for example graph's diameter computations or community detection).


Splay Trees

A splay tree is a self-adjusting binary search tree with the additional property that recently accessed elements are quick to access again. It performs basic operations such as insertion, look-up and removal in O(log n) amortized time. For many sequences of non-random operations, splay trees perform better than other search trees, even when the specific pattern of the sequence is unknown. The splay tree was invented by Daniel Sleator and Robert Tarjan in 1985.[1]

All normal operations on a binary search tree are combined with one basic operation, called splaying. Splaying the tree for a certain element rearranges the tree so that the element is placed at the root of the tree. One way to do this with the basic search operation is to first perform a standard binary tree search for the element in question, and then use tree rotations in a specific fashion to bring the element to the top. Alternatively, a top-down algorithm can combine the search and the tree reorganization into a single phase.

Splaying
When a node x is accessed, a splay operation is performed on x to move it to the root. To perform a splay operation we carry out a sequence of splay steps, each of which moves x closer to the root. By performing a splay operation on the node of interest after every access, the recently accessed nodes are kept near the root and the tree remains roughly balanced, so that we achieve the desired amortized time bounds.
Each particular step depends on three factors:
	•	Whether x is the left or right child of its parent node, p,
	•	whether p is the root or not, and if not
	•	whether p is the left or right child of its parent, g (the grandparent of x).
It is important to remember to set gg (the great-grandparent of x) to now point to x after any splay operation. If gg is null, then x obviously is now the root and must be updated as such.
There are three types of splay steps, each of which has a left- and right-handed case. For the sake of brevity, only one of these two is shown for each type. These three types are:
Zig step: this step is done when p is the root. The tree is rotated on the edge between x and p. Zig steps exist to deal with the parity issue and will be done only as the last step in a splay operation and only when x has odd depth at the beginning of the operation.

















Zig-zig step: this step is done when p is not the root and x and p are either both right children or are both left children. The picture below shows the case where x and p are both left children. The tree is rotated on the edge joining p with its parent g, then rotated on the edge joining x with p. Note that zig-zig steps are the only thing that differentiate splay trees from the rotate to root method introduced by Allen and Munro[4] prior to the introduction of splay trees.
Zig-zag step: this step is done when p is not the root and x is a right child and p is a left child or vice versa. The tree is rotated on the edge between p and x, and then rotated on the resulting edge between x and g.















Join
Given two trees S and T such that all elements of S are smaller than the elements of T, the following steps can be used to join them to a single tree:
	•	Splay the largest item in S. Now this item is in the root of S and has a null right child.
	•	Set the right child of the new root to T.
Split
Given a tree and an element x, return two new trees: one containing all elements less than or equal to x and the other containing all elements greater than x. This can be done in the following way:
	•	Splay x. Now it is in the root so the tree to its left contains all elements smaller than x and the tree to its right contains all element larger than x.
	•	Split the right subtree from the rest of the tree.
Insertion
To insert a value x into a splay tree:
	•	Insert x as with a normal binary search tree.
	•	when an item is inserted, a splay is performed.
	•	As a result, the newly inserted node x becomes the root of the tree.
ALTERNATIVE:
	•	Use the split operation to split the tree at the value of x to two sub-trees: S and T.
	•	Create a new tree in which x is the root, S is its left sub-tree and T its right sub-tree.
Deletion
To delete a node x, use the same method as with a binary search tree: if x has two children, swap its value with that of either the rightmost node of its left sub tree (its in-order predecessor) or the leftmost node of its right subtree (its in-order successor). Then remove that node instead. In this way, deletion is reduced to the problem of removing a node with 0 or 1 children. Unlike a binary search tree, in a splay tree after deletion, we splay the parent of the removed node to the top of the tree.


The Implementation :

The GMusic/GraphMusic App

The basic idea of the app is to create a basic ORM Non Sql database of our own and mould it in to a graphical database suited best for the social networking purpose of the app.

Basically the app has two main functionalities :

The Music Player 


The music player does the basic job of loading and playing music but there are some features exclusive to this, the songs are stored as MusicItem Objects and are stored in a splay tree, which lets us implement cache.

Now we can also benefit from splay tree with our search functionality , what happens is that the search becomes more efficient(as already discussed above ) and more natural.
Natural in a sense that with the search node, the songs similar to it or on a similar level also find place just below the searched song, hence working as suggestions functionality as well.

Now, some details:













This is the definition of MusicItem Class.

											P.T.O
Accounts are stored with hashing functionality , that takes into account both, username and password .

Accounts lets us make a social web, people joined can get suggestions of people they may know or the songs they may like.


















											P.T.O































Also , we get suggestions for songs












												P.T.O









All of this is done by taking in use the nature of connectivity of a graph database, the natural links achieved here make more sense and are much intuitive to operate as compared to any RDBMS joins.


Here is the BFS code used for suggesting the songs.







											P.T.O





Summary of the databases used and use cases:




