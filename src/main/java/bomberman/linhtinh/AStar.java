package bomberman.linhtinh;

import javafx.util.Pair;

import java.util.*;

public class AStar {
    private static double FLT_MAX = 10000;
    public class Point {
        public int x, y, xParent, yParent;
        public double f, g, h;
        // f = g + h

        public Point() {
            xParent = -1;
            yParent = -1;
            f = 0;
            g = 0;
            h = 0;
        }


    }

    class SortByF implements Comparator<Point> {
        // Used for sorting in ascending order of ID
        public int compare(Point a, Point b)
        {
            return (int) (a.f - b.f);
        }
    }

    public List<Pair<Integer, Integer>> aStarSearch(char[][] map, int ROW, int COL, int xSrc, int ySrc, int xDest, int yDest) {
        boolean[][] closeList = new boolean[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                closeList[i][j] = false;
            }
        }

        Point[][] points = new Point[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                points[i][j] = new Point();
            }
        }
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                points[i][j].x = i;
                points[i][j].y = j;
                points[i][j].f = FLT_MAX;
                points[i][j].g = FLT_MAX;
                points[i][j].h = FLT_MAX;
                points[i][j].xParent = -1;
                points[i][j].yParent = -1;
            }
        }

        points[xSrc][ySrc].x = xSrc;
        points[xSrc][ySrc].y = ySrc;
        points[xSrc][ySrc].f = 0.0;
        points[xSrc][ySrc].g = 0.0;
        points[xSrc][ySrc].h = 0.0;
        points[xSrc][ySrc].xParent = xSrc;
        points[xSrc][ySrc].yParent = ySrc;

        List<Point> openList = new ArrayList<>();
        openList.add(points[xSrc][ySrc]);

        boolean foundDest = false;

        while (!openList.isEmpty()) {
            Collections.sort(openList, new SortByF());
            Point p = openList.get(0);
            openList.remove(0);

            int xCurrent = p.x;
            int yCurrent = p.y;

            closeList[xCurrent][yCurrent] = true;

            double gNew, hNew, fNew;

            // Up
            if (isValid(xCurrent - 1, yCurrent, ROW, COL)) {
                if (isDestination(xCurrent - 1, yCurrent, xDest, yDest)) {
                    points[xCurrent - 1][yCurrent].xParent = xCurrent;
                    points[xCurrent - 1][yCurrent].yParent = yCurrent;
//                    System.out.println("The destination cell is found");
                    foundDest = true;
                    return tracePath(points, xDest, yDest);
                }
                else if (!closeList[xCurrent - 1][yCurrent]
                        && map[xCurrent - 1][yCurrent] == ' ') {
                    gNew = points[xCurrent][yCurrent].g + 1.0;
                    hNew = calculateHValue(xCurrent - 1, yCurrent, xDest, yDest);
                    fNew = gNew + hNew;

                    // If it isn’t on the open list, add it to
                    // the open list. Make the current square
                    // the parent of this square. Record the
                    // f, g, and h costs of the square cell
                    //                OR
                    // If it is on the open list already, check
                    // to see if this path to that square is
                    // better, using 'f' cost as the measure.
                    if (points[xCurrent - 1][yCurrent].f == FLT_MAX
                            || points[xCurrent - 1][yCurrent].f > fNew) {

                        // Update the details of this cell
                        points[xCurrent - 1][yCurrent].f = fNew;
                        points[xCurrent - 1][yCurrent].g = gNew;
                        points[xCurrent - 1][yCurrent].h = hNew;
                        points[xCurrent - 1][yCurrent].xParent = xCurrent;
                        points[xCurrent - 1][yCurrent].yParent = yCurrent;

                        openList.add(points[xCurrent - 1][yCurrent]);
                    }
                }
            }

            // Down
            if (isValid(xCurrent + 1, yCurrent, ROW, COL)) {
                if (isDestination(xCurrent + 1, yCurrent, xDest, yDest)) {
                    points[xCurrent + 1][yCurrent].xParent = xCurrent;
                    points[xCurrent + 1][yCurrent].yParent = yCurrent;
//                    System.out.println("The destination cell is found");
                    foundDest = true;
                    return tracePath(points, xDest, yDest);
                }
                else if (!closeList[xCurrent + 1][yCurrent]
                        && map[xCurrent + 1][yCurrent] == ' ') {
                    gNew = points[xCurrent][yCurrent].g + 1.0;
                    hNew = calculateHValue(xCurrent + 1, yCurrent, xDest, yDest);
                    fNew = gNew + hNew;

                    // If it isn’t on the open list, add it to
                    // the open list. Make the current square
                    // the parent of this square. Record the
                    // f, g, and h costs of the square cell
                    //                OR
                    // If it is on the open list already, check
                    // to see if this path to that square is
                    // better, using 'f' cost as the measure.
                    if (points[xCurrent + 1][yCurrent].f == FLT_MAX
                            || points[xCurrent + 1][yCurrent].f > fNew) {

                        // Update the details of this cell
                        points[xCurrent + 1][yCurrent].f = fNew;
                        points[xCurrent + 1][yCurrent].g = gNew;
                        points[xCurrent + 1][yCurrent].h = hNew;
                        points[xCurrent + 1][yCurrent].xParent = xCurrent;
                        points[xCurrent + 1][yCurrent].yParent = yCurrent;

                        openList.add(points[xCurrent + 1][yCurrent]);
                    }
                }
            }

            // Left
            if (isValid(xCurrent, yCurrent - 1, ROW, COL)) {
                if (isDestination(xCurrent, yCurrent - 1, xDest, yDest)) {
                    points[xCurrent][yCurrent - 1].xParent = xCurrent;
                    points[xCurrent][yCurrent - 1].yParent = yCurrent;
//                    System.out.println("The destination cell is found");
                    foundDest = true;
                    return tracePath(points, xDest, yDest);
                }
                else if (!closeList[xCurrent][yCurrent - 1]
                        && map[xCurrent][yCurrent - 1] == ' ') {
                    gNew = points[xCurrent][yCurrent].g + 1.0;
                    hNew = calculateHValue(xCurrent, yCurrent - 1, xDest, yDest);
                    fNew = gNew + hNew;

                    // If it isn’t on the open list, add it to
                    // the open list. Make the current square
                    // the parent of this square. Record the
                    // f, g, and h costs of the square cell
                    //                OR
                    // If it is on the open list already, check
                    // to see if this path to that square is
                    // better, using 'f' cost as the measure.
                    if (points[xCurrent][yCurrent - 1].f == FLT_MAX
                            || points[xCurrent][yCurrent - 1].f > fNew) {

                        // Update the details of this cell
                        points[xCurrent][yCurrent - 1].f = fNew;
                        points[xCurrent][yCurrent - 1].g = gNew;
                        points[xCurrent][yCurrent - 1].h = hNew;
                        points[xCurrent][yCurrent - 1].xParent = xCurrent;
                        points[xCurrent][yCurrent - 1].yParent = yCurrent;

                        openList.add(points[xCurrent][yCurrent - 1]);
                    }
                }
            }

            // Right
            if (isValid(xCurrent, yCurrent + 1, ROW, COL)) {
                if (isDestination(xCurrent, yCurrent + 1, xDest, yDest)) {
                    points[xCurrent][yCurrent + 1].xParent = xCurrent;
                    points[xCurrent][yCurrent + 1].yParent = yCurrent;
//                    System.out.println("The destination cell is found");
                    foundDest = true;
                    return tracePath(points, xDest, yDest);
                }
                else if (!closeList[xCurrent][yCurrent + 1]
                        && map[xCurrent][yCurrent + 1] == ' ') {
                    gNew = points[xCurrent][yCurrent].g + 1.0;
                    hNew = calculateHValue(xCurrent, yCurrent + 1, xDest, yDest);
                    fNew = gNew + hNew;

                    // If it isn’t on the open list, add it to
                    // the open list. Make the current square
                    // the parent of this square. Record the
                    // f, g, and h costs of the square cell
                    //                OR
                    // If it is on the open list already, check
                    // to see if this path to that square is
                    // better, using 'f' cost as the measure.
                    if (points[xCurrent][yCurrent + 1].f == FLT_MAX
                            || points[xCurrent][yCurrent + 1].f > fNew) {

                        // Update the details of this cell
                        points[xCurrent][yCurrent + 1].f = fNew;
                        points[xCurrent][yCurrent + 1].g = gNew;
                        points[xCurrent][yCurrent + 1].h = hNew;
                        points[xCurrent][yCurrent + 1].xParent = xCurrent;
                        points[xCurrent][yCurrent + 1].yParent = yCurrent;

                        openList.add(points[xCurrent][yCurrent + 1]);
                    }
                }
            }
        }

//        System.out.println("null");
//        System.out.println("");
        return null;
    }
    private boolean isValid(int r, int c, int ROW, int COL)
    {
        return (r >= 0) && (r < ROW) && (c >= 0)
                && (c < COL);
    }

    private boolean isDestination(int row, int col, int xDest, int yDest)
    {
        if (row == xDest && col == yDest)
            return (true);
        else
            return (false);
    }

    private double calculateHValue(int row, int col, int xDest, int yDest)
    {
        // Return using the distance formula
        return ((double) Math.sqrt(
                (row - xDest) * (row - xDest)
                        + (col - yDest) * (col - yDest)));
    }

    private List<Pair<Integer, Integer>> tracePath(Point[][] points, int xDest, int yDest) {
        Stack<Point> path = new Stack<>();

        int row = xDest;
        int col = yDest;
        while (!(points[row][col].xParent == row && points[row][col].yParent == col)) {
            path.push(points[row][col]);
            int tempRow = points[row][col].xParent;
            int tempCol = points[row][col].yParent;
            row = tempRow;
            col = tempCol;
        }

        List<Pair<Integer, Integer>> tempList = new ArrayList<>();
        while (!path.isEmpty()) {
            Point point = path.pop();
            Pair<Integer, Integer> p = new Pair<>(point.x, point.y);
            tempList.add(p);
        }
//        System.out.println(tempList);
//        System.out.println("");
        return tempList;
    }
}
