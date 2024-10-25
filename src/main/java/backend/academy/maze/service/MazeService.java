package backend.academy.maze.service;

import backend.academy.maze.dto.CreateMazeRequestDTO;
import backend.academy.maze.dto.SolveMazeRequestDTO;
import backend.academy.maze.models.GraphMaze;
import backend.academy.maze.models.Solution;

public interface MazeService {
    GraphMaze createMaze(CreateMazeRequestDTO createMazeRequestDTO);

    Solution solveMaze(SolveMazeRequestDTO solveMazeRequestDTO);
}
