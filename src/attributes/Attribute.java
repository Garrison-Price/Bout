package attributes;

/**
 *
 * @author Garrison Price
 */
public class Attribute {
    protected int minValue;
    protected int currentValue;
    protected int maxValue;
    
    public Attribute(int maxValue, int startingValue, int minValue) {
        this.minValue = minValue;
        this.currentValue = startingValue;
        this.maxValue = maxValue;
    }
    
    public boolean decrement() {
        return decrement(1);
    }
    
    public boolean decrement(int decrementValue) {
        if(!checkBottom(decrementValue))
            return false;
        currentValue -= decrementValue;
        return true;
    }
    
    private boolean checkBottom(int decrementValue) {
        return (currentValue - decrementValue < minValue);
    }
    
    public boolean increment() {
        return increment(1);
    }
    
    public boolean increment(int incrementValue) {
        if(!checkTop(incrementValue))
            setToMaxValue();
        else
            currentValue += incrementValue;
        return true;
    }
    
    private boolean checkTop(int incrementValue) {
        return (currentValue + incrementValue >= maxValue);
    }
    
    public void setToMaxValue() {
        currentValue = maxValue;
    }
    
    public boolean overloadIncrement() {
        return overloadIncrement(1);
    }
    
    public boolean overloadIncrement(int incrementValue) {
        currentValue += incrementValue;
        return true;
    }
    
    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }
    
    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }
}
