package fluent.ly;

import static fluent.ly.azzert.*;
import static fluent.ly.box.*;

import java.util.*;

import org.jetbrains.annotations.*;
import org.junit.*;

import il.org.spartan.*;

@SuppressWarnings("static-method") public class hasTest {
  List<@Nullable Object> list;

  @Before public void initList() {
    list = new ArrayList<>();
    list.add(box(1));
    list.add(box(2));
    list.add(box(3));
    list.add(box(4));
    list.add(box(5));
  }

  @Test public void getItemInRange() {
    azzert.that(has.next(2, Utils.cantBeNull(list)), is(box(4)));
  }

  @Test public void getItemOutOfRange() {
    azzert.that(has.next(1000, Utils.cantBeNull(list)), is(box(5)));
  }

  @Test public void iterableHasNoNulls() {
    final @NotNull Iterable<@Nullable Object> it = Utils.cantBeNull(list);
    assert !has.nulls(it);
  }

  @Test public void iterableHasNulls() {
    list.add(null);
    final @NotNull Iterable<@Nullable Object> it = Utils.cantBeNull(list);
    assert has.nulls(it);
  }

  @Test public void varargsHasNoNulls() {
    assert !has.nulls(box(1.5), box('H'), box(true));
  }

  @Test public void varargsHasNulls() {
    assert has.nulls(box(6), box('j'), null, box(3.14159));
  }

  @Test public void sendNull() {
    assert has.nulls((String) null);
  }
}
