package de.rieckpil.courses.book.review;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
//@Testcontainers(disabledWithoutDocker = true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReviewRepositoryNoInMemoryTest {
  //@Container
  static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:12.3")
    .withDatabaseName("test")
    .withUsername("duke")
    .withPassword("s3cret")
    .withReuse(true);

  static {
    container.start();
  }

  @DynamicPropertySource
  static void properties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", container::getJdbcUrl);
    registry.add("spring.datasource.password", container::getPassword);
    registry.add("spring.datasource.username", container::getUsername);
  }

  @Autowired
  private ReviewRepository cut;

  @Test
  @Sql(scripts = "/scripts/INIT_REVIEW_EACH_BOOK.sql")
  void shouldGetTwoReviewStatisticsWhenDatabaseContainsTwoBooksWithReview() {
  }

  @Test
  void databaseShouldBeEmpty() {
  }
}
