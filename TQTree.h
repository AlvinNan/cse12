#include <stdlib.h>

typedef struct TQNode TQNode;
typedef struct TQTree TQTree;

static const int MAX_LENGTH = 80;

struct TQNode {
    TQNode* yesChild;
    TQNode* noChild;
    char* data;
    int idx;
};

struct TQTree {
    TQNode* root;
};

// A safe way to read string data from the user
// Reads length characters into data (including 
// the null character) from stdin and ignores the 
// rest of the line.
// @param data buffer to read into
// @param length length of data to read
void readAnswer(char* data, int length);

// creates a node on the heap.
// allocates space for both the node and the string.
// @returns TQNode*
TQNode* newNode(char* data);

// creates a tree on the heap
// @returns TQTree*
TQTree* newTree();

// free all nodes and data from memory
// @param tree
void delTree(TQTree* tree);

// recursively delete nodes in tree
// @param root
void delTreeHelper(TQNode* root);

// build tree with initial question and answers
// @param tree pointer to game tree
void buildDefaultTree(TQTree* tree);

// save game tree to file
// @param tree pointer to game tree
// @param filename file to save tree to
void save(struct TQTree* tree, char* filename);

// recursive helper to save tree to file
// @param node node to write to
// @param fptr file pointer to write to
void saveTree(struct TQNode* node, FILE* fptr);

// plays one round of the Twenty Questions game
// @param tree pointer to game tree
void play(TQTree* tree);

// Print a level-order traversal of the tree to standard out
// plays one round of the Twenty Questions game
// @param tree pointer to game tree
void print(TQTree* tree);

// HELPER METHODS
// You will likely want to define some helper methods here.
