function checkNeighborhood(cells, x, y) {
    headCounter = 0
    if(cells[x - 1][y - 1] == 2){
        headCounter += 1
    }
    if (cells[x - 1][y] == 2){
        headCounter += 1
    }
    if (cells[x - 1][y + 1] == 2){
        headCounter += 1
    }
    if (cells[x][y - 1] == 2){
        headCounter += 1
    }
    if (cells[x][y + 1] == 2){
        headCounter += 1
    }
    if (cells[x + 1][y - 1] == 2){
        headCounter += 1
    }
    if(cells[x + 1][y] == 2){
        headCounter += 1
    }
    if(cells[x + 1][y + 1] == 2){
        headCounter += 1
    }
    return headCounter
}

function nextStep(cells, nextCells, rows, columns) {
    for(x = 1; x < rows - 1; x++){
        for(y = 1; y < columns - 1; y++){

            if(cells[x][y] == 2){
                nextCells[x][y] = 3
            }else if(cells[x][y] == 3){
                nextCells[x][y] = 1
            }else if(cells[x][y] == 1 && (checkNeighborhood(cells, x, y) == 1 || checkNeighborhood(cells, x, y) == 2)){
                nextCells[x][y] = 2
            }
        }
    }
    return nextCells
  }