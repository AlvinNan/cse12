#include <stdio.h>
#include <string.h>
#include "TQTree.h"

// A safe way to read string data from the user
void readAnswer(char* data, int length) {
    char ch;
    fgets(data, length, stdin);
    if (data[strlen(data)-1] != '\n') {
        // If we didn't read the whole line, eat the rest of the line
        while (((ch = getchar()) != '\n')  && (ch != EOF));
    }
    else {
        // Otherwise get rid of the newline we read
        data[strlen(data)-1] = '\0';
    }
}

// Make a new node to hold the data in data
TQNode* newNode(char* data) {
    // Try to allocate vector structure.
    TQNode *currNode = malloc(sizeof(TQNode));
    if (currNode == NULL)
        return NULL;

    // Try to allocate vector data, free structure if fail.
    currNode->data = malloc(MAX_LENGTH * sizeof (char));
    if (currNode->data == NULL) {
        free(currNode);
        return NULL;
    }
    
    strcpy(currNode->data, data);
    currNode->noChild = NULL;
    currNode->yesChild = NULL;
    return currNode;
}

// Build a new tree
TQTree* newTree() {
    TQTree *tree = malloc (sizeof (TQTree));
    return tree;
}

// Free the memory in the Tree
void delTree(TQTree* tree) {
    printf("Freeing the game tree\n");
    delTreeHelper(tree->root);
    free(tree);
}

// Recursively delete the nodes and the data strings 
// in the tree via a post-order traversal.
// This method should:
//    do nothing and return if root is NULL
//    else it should:
//        recursively delete root's yesChild
//        recursively delete root's noChild
//        delete (free) root's data
//        delete (free) the root
void delTreeHelper(TQNode* root) {
    // TODO: Implement this method
	if(root == NULL) return;
	else{
		delTreeHelper(root->noChild);
		delTreeHelper(root->yesChild);
		free(root->data);
		free(root);
	}
}

void buildDefaultTree(TQTree* tree) {
    tree->root = newNode("Is it bigger than a breadbox?");
    tree->root->noChild = newNode("spam");
    tree->root->yesChild = newNode("a computer scientist");
}


void save(struct TQTree* tree, char* filename) {
    FILE* fptr = fopen(filename, "w");
    if (fptr == NULL) {
        printf("Error opening file!\n");
        exit(1);
    }
    saveTree(tree->root, fptr);
    fclose(fptr);
}

void saveTree(struct TQNode* node, FILE* fptr) {
    if (node == NULL) {
        return;
    }
    if (node->noChild == NULL && node->yesChild == NULL) {
        fprintf(fptr, "A:%s\n", node->data);
    } else {
        fprintf(fptr, "Q:%s\n", node->data);
    }
    saveTree(node->noChild, fptr);
    saveTree(node->yesChild, fptr);
}

void play(TQTree* tree) {
    // TODO: Implement this method
	int end = 0;
	char response[2];
	printf("%s\n", tree->root->data);
	TQNode* currNode = tree->root;
	while(1){
		readAnswer(response, 2);
		//When user inputs yes
		if(strcmp(response, "y")){
			TQNode* yesChildptr = newNode(currNode->yesChild->data);
			TQNode* noChildptr = newNode(currNode->noChild->data);
			//End the game
			if(end == 1) break;
			//If currentNode is a leaf node
			//Computer won!
			if(yesChildptr == NULL && noChildptr == NULL){
				printf("I won!\n");
				break;
			}
			//If yesChild is not null but a leaf node
			//Take a guess
			if(yesChildptr != NULL && currNode->yesChild->yesChild == NULL){
				printf("Is it %s ?\n", currNode->yesChild->data);
			}
			//If yesChild is not null nor a leaf node
			//Ask question
			if(yesChildptr != NULL && currNode->yesChild->yesChild != NULL){
				printf("%s",currNode->yesChild->data);
			}
			currNode = yesChildptr;
		}
		//When user inputs anything other than yes
		if(!strcmp(response, "y")){
			TQNode *yesChildptr = currNode->yesChild;
			TQNode*noChildptr = newNode(currNode->noChild->data);

			//If noChild is not null but a leaf node
			//Take a guess
			if(noChildptr != NULL && currNode->noChild->yesChild == NULL && end == 0){
				printf("Is it %s ?\n", currNode->yesChild->data);
				currNode = noChildptr;
			}
			//If noChild is not null nor a leaf node
			//Ask question
			if(noChildptr != NULL && currNode->noChild->yesChild != NULL){
				printf("%s", currNode->yesChild->data);
				currNode = noChildptr;
			}
			//If currentNode is a leaf node
			//Ask what was it
			if(yesChildptr == NULL && noChildptr == NULL){
				printf("Ok, what was it?\n");
				//Record the new object and
				//ask for a question to ask next time
				readAnswer(response, 2);
				currNode->noChild = newNode(currNode->noChild->data);
				currNode->yesChild = newNode(response);
				printf("Give me a question that would distinguish %s from %s\n",
						response, currNode->noChild->data);
				//Record the new question and
				//ask how to place the new object
				readAnswer(response, 2);
				strcpy(currNode->data, response);
				printf("And would the answer to the question for %s be yes or no\n",
						response);
				end = 1;
			}
			//Swap yes and no child
			else if(end == 1){
				TQNode* temp = newNode(currNode->yesChild->data);
				yesChildptr = noChildptr;
				noChildptr = temp;
				free(temp);
				break;
			}
		}

	}
}

void print(TQTree* tree) {
    // TODO: Implement this method  (Optional)
    printf("The print method has not yet been implemented");
}
