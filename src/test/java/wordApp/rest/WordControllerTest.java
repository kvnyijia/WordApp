package wordApp.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import wordApp.service.WordService;
import wordApp.service.TableService;
import wordApp.entity.Table;
import wordApp.rest.word_class.CreateWordReq;

// Add these imports
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import org.springframework.security.test.context.support.WithMockUser;

@WebMvcTest(WordController.class)
public class WordControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private WordService wordService;

  @MockBean
  private TableService tableService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @WithMockUser(username = "testuser")  // Avoid 401 Unauthorized
  void shouldCreateNewWord() throws Exception {
    // Arrange
    CreateWordReq req = new CreateWordReq();
    req.setTable_id(1);
    req.setTerm("hello");
    req.setMeaning("greeting");
    req.setPicture_url("http://example.com/hello.jpg");

    Table mockTable = new Table();
    mockTable.setId(1);

    when(tableService.find(1)).thenReturn(mockTable);

    // Act & Assert
    mockMvc.perform(post("/words")
      .with(SecurityMockMvcRequestPostProcessors.csrf())  // Avoid 403 Forbidden
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(req)))
      .andExpect(status().isCreated())
      .andExpect(content().string("true"));
  }

  @Test
  void shouldRetrieveWord() {
    // Test word retrieval
  }

  @Test
  void shouldUpdateWord() {
    // Test word update
  }

  @Test
  void shouldDeleteWord() {
    // Test word deletion
  }
}
