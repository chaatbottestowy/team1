package il.org.spartan;

import static il.org.spartan.Utils.*;

import static fluent.ly.azzert.*;
import static fluent.ly.box.*;

import java.io.*;
import java.util.*;

import org.jetbrains.annotations.*;
import org.junit.*;

import fluent.ly.*;

@SuppressWarnings("static-method") public class UtilsTest {
  @NotNull public static Integer[] intToIntegers(final int... is) {
    final @NotNull Integer @NotNull [] $ = new @NotNull Integer @NotNull [is.length];
    for (int ¢ = 0; ¢ < is.length; ++¢)
      $[¢] = fluent.ly.box.it(is[¢]);
    return $;
  }

  @Test @SuppressWarnings("unchecked") public void addAllTypical() {
    final Set<String> ss = new HashSet<>();
    accumulate.to(ss).addAll(as.set("A", "B"), null, as.set("B", "C", "D"));
    azzert.nay(ss.contains("E"));
    azzert.nay(ss.contains(null));
    azzert.that(ss.size(), is(4));
    for (final String ¢ : ss)
      azzert.aye("", ss.contains(¢));
  }

  @Test public void addTypical() {
    final Set<String> ss = new HashSet<>();
    accumulate.to(ss).add(null, "A", null, "B", "B", null, "C", "D", null);
    azzert.nay(ss.contains("E"));
    azzert.nay(ss.contains(null));
    azzert.that(ss.size(), is(4));
    for (final String ¢ : ss)
      azzert.aye("", ss.contains(¢));
    azzert.aye(ss.contains("A"));
  }

  @Test(expected = AssertionError.class) public void cantBeNullOfNull() {
    cantBeNull(null);
//    azzert.fail("AssertionError expected prior to this line.");
  }

  @Test public void cantBeNullTypical() {
    azzert.notNull(cantBeNull(new Object()));
  }

  @Test (expected = AssertionError.class) public void isNullTypical() {
      isNull(Utils.mustBeNull(null));
     azzert.fail("AssertionError expected prior to this line.");

  }

  @Test (expected = AssertionError.class)  public void mustBeNullOfNotNull() {
      Utils.mustBeNull(new Object());
      azzert.fail("AssertionError expected prior to this line.");
   
  }

  @Test public void swapDegenerate() {
    final @NotNull String[] ss = as.array("A", "B", "C", "D");
    Utils.swap(ss, 1, 1);
    azzert.that(as.array("A", "B", "C", "D"), is(ss));
  }

  @Test public void swapTypical() {
    final @NotNull String[] ss = as.array("A", "B", "C", "D");
    Utils.swap(ss, 1, 2);
    azzert.that(as.array("A", "C", "B", "D"), is(ss));
  }

  @Test public void swapTypicalCase() {
    final @NotNull Integer[] $ = intToIntegers(29, 1, 60);
    Utils.swap($, 0, 1);
    azzert.that(intToIntegers(1, 29, 60), is($));
  }

  @Test public void collectionTesting() {
    final List<Integer> x = new ArrayList<>();
    x.add(box(4));
    x.add(box(5));
    final List<Integer> y = new ArrayList<>();
    y.add(box(5));
    final List<Integer> yy = new ArrayList<>();
    yy.add(null);
    azzert.notNull(Utils.apply(λ -> λ).to(y));
    azzert.notNull(Utils.apply(λ -> λ).to(yy));
    azzert.notNull(Utils.apply(λ -> λ).to(new Object()));
    azzert.that(Utils.hash(null), is(0));
    final Object o = new Object();
    azzert.that(Utils.hash(o), is(o.hashCode()));
    final List<List<Integer>> X = new ArrayList<>();
    azzert.that(Utils.add(X, x), is(X));
    x.add(null);
    azzert.that(Utils.add(X, yy), is(X));
    azzert.that(Utils.add(x, y), is(x));
    azzert.that(Utils.addAll(X, yy), is(X));
    azzert.that(Utils.addAll(y, yy), is(y));
    azzert.that(Utils.addAll(y, (Iterable<Integer>) x), is(y));
    assert !Utils.hasNull(yy);
    assert Utils.hasNull(box(1), null);
    azzert.notNull(Utils.append(new @NotNull Integer[0], box(4))[0]);
    azzert.notNull(Utils.delete(new @NotNull Integer[4], 3));
    assert Utils.inRange(0, y);
    assert !Utils.inRange(-70, y);
    assert !Utils.inRange(5, y);
    assert Utils.intIsIn(1, 1, 2, 3);
    assert !Utils.intIsIn(606, 1, 2, 3);
    assert Utils.lastIn(box(5), x);
    assert !Utils.lastIn(box(2), x);
    assert Utils.lastIn(box(5), x);
    @Nullable final List<@Nullable Integer> z = new ArrayList<>();
    z.add(box(4));
    z.add(box(5));
    Utils.removeDuplicates(z);
    assert !Utils.penultimateIn(box(5), z);
    assert Utils.penultimateIn(box(4), z);
  }

  @Test public void utilTesting() {
    azzert.notNull(canBeNull(box(3)));
    // azzert.assertNotNull(Utils.apply(λ->λ).to(box(1),));
    azzert.that(Utils.compare(true, true), is(0));
    azzert.that(Utils.compare(false, true), is(-1));
    azzert.that(Utils.compare(true, false), is(1));
    azzert.that(Utils.sqr(1.0), is(1.0));
    assert Utils.found(5).in(1, 2, 3, 4, 5);
    assert !Utils.found(6).in(1, 2, 3, 4, 5);
    azzert.notNull(Utils.found(box(5)));
    azzert.notNull(Utils.sort(new int @NotNull [] { 1, 2, 3, 4 }));
  }

  @Test public void stringTesting() {
    azzert.that(Utils.compressSpaces("HHH            HHH"), is("HHH HHH"));
    assert Utils.contains("HHH            HHH", "HHH");
    assert !Utils.contains("HHH            HHH", "HgHH");
    azzert.that(Utils.name(new File("Hi")), is(new File("Hi").getName()));
    final List<String> X = new ArrayList<>();
    assert !Utils.suffixedBy(new File("Hi"), X);
    assert !Utils.suffixedBy(new File("Hi"), "HHH");
    X.add("Hi");
    assert Utils.suffixedBy(new File("Hi"), X);
    assert Utils.suffixedBy(new File("Hi"), "Hi");
    azzert.that(Utils.prepend(new StringBuilder(), 'c') + "", is("c"));
    azzert.that(Utils.prepend(new StringBuilder(), "ac") + "", is("ac"));
    azzert.that(Utils.prepend(new StringBuilder(), "ac") + "", is("ac"));
    azzert.that(Utils.removePrefix("Maaaaaaaa", "M"), is("aaaaaaaa"));
    azzert.that(Utils.removeSuffix("Maaaaaaaa", "aaaaaaaa"), is("M"));
    azzert.that(Utils.removeWhites("  "), is(""));
  }
}
