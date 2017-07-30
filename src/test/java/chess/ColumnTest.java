package chess;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ColumnTest {

    @Test(expected = IllegalArgumentException.class)
    public void leftShouldFailOnUnknownSymbol() {
        Column.left('v');
    }

    @Test
    public void leftShouldSucced() {
        assertThat(Column.left('b')).isEqualTo('a');
    }

    @Test
    public void leftShouldReturnNull() {
        assertThat(Column.left('a')).isNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void rightShouldFailOnUnjknownSymbol() {
        Column.right('v');
    }

    @Test
    public void rightShouldSucced() {
        assertThat(Column.right('b')).isEqualTo('c');
    }

    @Test
    public void rightShouldReturnNull() {
        assertThat(Column.right('h')).isNull();
    }
}