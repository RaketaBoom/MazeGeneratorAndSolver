package backend.academy.maze.surface;

import backend.academy.maze.enums.Surface;
import backend.academy.maze.exceptions.IllegalEarthProbabilityException;
import java.util.Random;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RandomSurfaceGenerator {
    private final Random random;

    public Surface getSurface(double earthProbability) {
        if (earthProbability > 1) {
            throw new IllegalEarthProbabilityException();
        }
        // Генерируем случайное число от 0 до 1
        double randomValue = random.nextDouble();

        if (randomValue <= earthProbability) {
            return Surface.EARTH;
        } else {
            double boundSwampMagicStones = earthProbability + (1 - earthProbability) / 2;
            if (randomValue <= boundSwampMagicStones) {
                return Surface.SWAMP;
            } else {
                return Surface.MAGIC_STONES;
            }
        }
    }
}
