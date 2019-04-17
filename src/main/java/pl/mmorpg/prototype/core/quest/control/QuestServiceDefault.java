package pl.mmorpg.prototype.core.quest.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mmorpg.prototype.core.quest.boundary.QuestService;
import pl.mmorpg.prototype.data.entities.Quest;
import pl.mmorpg.prototype.data.entities.repositories.QuestRepository;

@Service
public class QuestServiceDefault implements QuestService
{
	private final QuestRepository questRepository;

	@Autowired
	public QuestServiceDefault(QuestRepository questRepository)
	{
		this.questRepository = questRepository;
	}

	@Override
	public Iterable<Quest> findAllQuestDefinitions()
	{
		return questRepository.findAll();
	}
}
