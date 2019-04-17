package pl.mmorpg.prototype.core.quest.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mmorpg.prototype.data.entities.Quest;

@RestController
@RequestMapping("/quests")
public class QuestController
{
	private final QuestService questService;

	@Autowired
	public QuestController(QuestService questService)
	{
		this.questService = questService;
	}

	@GetMapping("/definitions")
	public Iterable<Quest> getQuestDefinitions() {
		return questService.findAllQuestDefinitions();
	}
}
