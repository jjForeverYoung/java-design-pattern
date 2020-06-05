/*
 * The MIT License
 * Copyright © 2014-2019 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.iluwatar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Tests {@link Customer}.
 */
public class CustomerTest {

  private Customer customer;
  private static final int ID = 1;
  private static final String FIRSTNAME = "Winston";
  private static final String LASTNAME = "Churchill";

  @BeforeEach
  public void setUp() {
    customer = new Customer(ID, FIRSTNAME, LASTNAME);
  }

  @Test
  public void getAndSetId() {
    final var newId = 2;
    customer.setId(newId);
    assertEquals(newId, customer.getId());
  }

  @Test
  public void getAndSetFirstname() {
    final var newFirstname = "Bill";
    customer.setFirstName(newFirstname);
    assertEquals(newFirstname, customer.getFirstName());
  }

  @Test
  public void getAndSetLastname() {
    final var newLastname = "Clinton";
    customer.setLastName(newLastname);
    assertEquals(newLastname, customer.getLastName());
  }

  @Test
  public void notEqualWithDifferentId() {
    final var newId = 2;
    final var otherCustomer = new Customer(newId, FIRSTNAME, LASTNAME);
    assertNotEquals(customer, otherCustomer);
    assertNotEquals(customer.hashCode(), otherCustomer.hashCode());
  }

  @Test
  public void equalsWithSameObjectValues() {
    final var otherCustomer = new Customer(ID, FIRSTNAME, LASTNAME);
    assertEquals(customer, otherCustomer);
    assertEquals(customer.hashCode(), otherCustomer.hashCode());
  }

  @Test
  public void equalsWithSameObjects() {
    assertEquals(customer, customer);
    assertEquals(customer.hashCode(), customer.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals(String.format("Customer{id=%s, firstName='%s', lastName='%s'}",
        customer.getId(), customer.getFirstName(), customer.getLastName()), customer.toString());
  }
}
