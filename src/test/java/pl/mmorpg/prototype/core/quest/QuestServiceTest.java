package pl.mmorpg.prototype.core.quest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.mmorpg.prototype.core.quest.boundary.QuestService;
import pl.mmorpg.prototype.data.entities.Quest;
import pl.mmorpg.prototype.data.entities.repositories.QuestRepository;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@Transactional
class QuestServiceTest
{
	@Autowired
	private QuestService questService;

	@Autowired
	private QuestRepository questRepository;

	@Test
	void findSavedQuest()
	{
		Quest quest = new Quest();
		quest.setName("questName");
		quest.setDescription("questDescription");
		quest.setQuestTask(new TestableEntityQuestTask());
		questRepository.save(quest);

		Iterable<Quest> result = questService.findAllQuestDefinitions();

		assertThat(result).extracting("id").containsExactly(quest.getId());
	}
}
