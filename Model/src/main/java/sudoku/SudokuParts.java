package sudoku;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuParts implements Serializable, Cloneable {

    private final List<SudokuField> part = Arrays.asList(new SudokuField[9]);

    public SudokuField[] getPart() {
        return part.toArray(SudokuField[]::new);
    }

    public void setPart(SudokuField[] fields) {
        for (int i = 0; i < part.size(); i++) {
            part.set(i, fields[i]);
        }
    }

    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (part.get(j).getFieldValue() == part.get(i).getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    // toString, equals, hashCode

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("List Part", part)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        SudokuParts rhs = (SudokuParts) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(part, rhs.part)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(part)
                .toHashCode();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
