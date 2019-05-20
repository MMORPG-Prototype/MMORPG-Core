package pl.mmorpg.prototype.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@Transactional
class MmorpgCoreApplicationTests
{
	@Test
	void contextLoads()
	{
	}
}
