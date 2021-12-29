package sudoku;

import expections.OutOfRangeException;
import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuField implements Serializable, Comparable<SudokuField>, Cloneable {

    private int value;

    public int getFieldValue() {
        return value;
    }


    public void setFieldValue(int value) {
        if (value < 0 || value > 9) {
            throw new OutOfRangeException("EXCEPTION: Bad field values!");
        }
        this.value = value;
    }

    // toString, equals, hashCode

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value", value)
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
        SudokuField rhs = (SudokuField) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(value, rhs.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(value)
                .toHashCode();
    }


    // Comparable
    @Override
    public int compareTo(SudokuField field) {

        return Integer.compare(value, field.value);
    }

    // Cloneable
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
