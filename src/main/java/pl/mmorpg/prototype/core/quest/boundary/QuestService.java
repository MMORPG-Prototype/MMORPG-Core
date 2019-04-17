package pl.mmorpg.prototype.core.quest.boundary;

import pl.mmorpg.prototype.data.entities.Quest;

public interface QuestService
{
	Iterable<Quest> findAllQuestDefinitions();
}
