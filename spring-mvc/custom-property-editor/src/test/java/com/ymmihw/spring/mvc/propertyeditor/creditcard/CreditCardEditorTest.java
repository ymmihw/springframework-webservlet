package com.ymmihw.spring.mvc.propertyeditor.creditcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreditCardEditorTest {

  private CreditCardEditor creditCardEditor;

  @BeforeEach
  public void setup() {
    creditCardEditor = new CreditCardEditor();
  }

  @Test
  public void whenInvalidCardNoWithLessDigits_thenThrowsException() {
    assertThrows(IllegalArgumentException.class,
        () -> creditCardEditor.setAsText("123-123-123-123"));

  }

  @Test
  public void whenInvalidCardNoWithNonDigits_thenThrowsException() {
    assertThrows(IllegalArgumentException.class,
        () -> creditCardEditor.setAsText("1234-1234-xxxx-yyyy"));
  }

  @Test
  public void whenCardNoWithNonDigits_parseCreditCard() {
    creditCardEditor.setAsText("1234-5678-9123-4560");

    CreditCard creditCard = (CreditCard) creditCardEditor.getValue();
    assertNotNull(creditCard);

    assertEquals(123456, creditCard.getBankIdNo().intValue());
    assertEquals(789123456, creditCard.getAccountNo().intValue());
    assertEquals(0, creditCard.getCheckCode().intValue());
  }

}
