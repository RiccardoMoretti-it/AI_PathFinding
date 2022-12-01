package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Model.Entities.Entity;
import Model.Entities.Characters.Robot;

public class utilities {
    public static boolean between(int i, int minValueInclusive, int maxValueInclusive) {
        return (i >= minValueInclusive && i <= maxValueInclusive);
    }

	public static ArrayList<Robot> getAllRobots(Environment env){
		List<Optional<Entity>> allOptionalRobots = env.getEntitiesWithCondition(bot ->(bot instanceof Robot));
		ArrayList<Robot> AllRobots=new ArrayList<>();
		allOptionalRobots.forEach(i->AllRobots.add((Robot)i.get()));
		return AllRobots;
	}
}
