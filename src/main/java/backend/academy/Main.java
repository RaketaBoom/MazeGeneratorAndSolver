package backend.academy;

import backend.academy.maze.MazeApi;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        MazeApi mazeApi = new MazeApi(System.in, System.out);
        mazeApi.start();
    }
}
