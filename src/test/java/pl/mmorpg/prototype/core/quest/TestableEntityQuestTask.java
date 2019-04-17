package pl.mmorpg.prototype.core.quest;

import pl.mmorpg.prototype.data.entities.components.EntityQuestTask;
import pl.mmorpg.prototype.data.entities.jointables.CharactersQuests;

import java.io.Serializable;
import java.util.List;

public class TestableEntityQuestTask implements EntityQuestTask, Serializable
{
	@Override
	public void setSourceTask(CharactersQuests sourceTask)
	{
	}

	@Override
	public List<EntityQuestTask> getNextEntityTasks()
	{
		return null;
	}
}
