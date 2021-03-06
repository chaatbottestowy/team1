package il.org.spartan.utils;

import static il.org.spartan.Utils.*;

import static fluent.ly.azzert.*;

import org.junit.*;

import fluent.ly.*;

public class StrTest {
  Str empty_str = new Str();
  Str valued_Str = new Str("Hello world!");

  @Test public void setTest() {
    assert !valued_Str.isEmptyx();
    azzert.that(cantBeNull(valued_Str.inner()), is("Hello world!"));
    valued_Str.set("newValue");
    assert !valued_Str.isEmptyx();
    azzert.that(valued_Str.inner(), is("newValue"));
  }

  @Test public void innerTest() {
    azzert.that(valued_Str.inner(), is("Hello world!"));
    azzert.that(empty_str.inner(), is(canBeNull((String) null)));
  }

  @Test public void isEmptyTest() {
    assert !valued_Str.isEmptyx();
    assert empty_str.isEmptyx();
  }

  @Test public void notEmptyTest() {
    assert valued_Str.notEmpty();
    assert !empty_str.notEmpty();
  }
}