#include <stdio.h>
#include <string.h>
#include "TQTree.h"

// Save the tree to a file whose name is entered by the user.
void saveGame(TQTree* theGame) {
    printf("Please enter a filename to save your game\n");
    char response[64];
    readAnswer(response, 64);
    save(theGame, response);
    printf("Game saved.\n");
}

// The main function that starts and controls the game.
int main (int argc, char *argv[]) { 
    TQTree* gameTree = NULL;
    gameTree = newTree();
    buildDefaultTree(gameTree);
    printf("building default tree\n");

    char response[2];
    response[0] = 'y';
    response[1] = '\0';

    while (!strcmp(response, "y")) {
        play(gameTree);
        printf("Would you like to play again?\n");
        readAnswer(response, 2);
    }

    printf("Would you like to save your game tree?\n");
    readAnswer(response, 2);
    if (!strcmp(response, "y")) {
        saveGame(gameTree);
    }
    
    delTree(gameTree);
    printf("Goodbye!\n");
    return 0;
}
