package interview.utils;

import interview.utils.functions.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.testng.AssertJUnit.assertTrue;

public class VirtualElementList implements Iterable<VirtualElement>, Iterator<VirtualElement> {

    private List<VirtualElement> virtualElements;
    private By by;
    private int count = 0;
    public VirtualElementList(By by){
        this.by=by;
    }

    public VirtualElement get(int index){
        if(elementNotNull())
            return virtualElements.get(index);
        return null;
    }

    public int size(){
        if(elementNotNull())
            return virtualElements.size();
        return -1;
    }

    public boolean contains(VirtualElement virtualElement){
        if(elementNotNull())
            return virtualElements.contains(virtualElement);
        return false;
    }

    public int indexOf(VirtualElement virtualElement){
        if(elementNotNull())
            return virtualElements.indexOf(virtualElement);
        return -1;
    }

    public VirtualElement getFirst(){
        if(elementNotNull())
            return virtualElements.get(0);
        return null;
    }

    public VirtualElement getFirstWhere(Predicate<? super VirtualElement> predicate){
        if(elementNotNull()){
            return virtualElements.stream().filter(predicate).findFirst().orElse(null);
        }
        return null;
    }

    public ArrayList<VirtualElement> where(Predicate<? super VirtualElement> predicate){
        if(elementNotNull()){
            Stream<VirtualElement> query = virtualElements.stream().filter(predicate);
            if(query!=null){
                return (ArrayList<VirtualElement>) query.collect(Collectors.toList());
            }
        }
        return null;
    }

    public ArrayList<VirtualElement> sort(Comparator<? super VirtualElement> comparator){
        if(elementNotNull())
            return (ArrayList<VirtualElement>) virtualElements.stream().sorted(comparator).collect(Collectors.toList());
        return null;
    }

    public VirtualElement getLast(){
        if(elementNotNull())
            return virtualElements.get(size()-1);
        return null;
    }

    //Metodo de Verificação se o elemento esta nulo ou se precisa ser pego ainda.
    private boolean elementNotNull() {
        if(this.virtualElements==null) {
            try{
                new Wait().waitToBeLocated(this.by);
                List<WebElement> list = WebController.getInstance().getDriver_().findElements(this.by);
                virtualElements = new ArrayList<VirtualElement>();
                for(WebElement webElement : list){
                    virtualElements.add(new VirtualElement(webElement));
                }
            }catch (Exception ignored){}

        }
        assertTrue("VirtualElement Não pode ser null", this.virtualElements!=null);
        return true;
    }

    // The next three methods implement Iterator.
    public boolean hasNext() {
        if(elementNotNull()){
            if (count < virtualElements.size()){
                return true;
            }
            return false;
        }
        return false;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<VirtualElement> iterator() {
        return this;
    }

    @Override
    public VirtualElement next() {
        if (count == virtualElements.size())
            throw new NoSuchElementException();

        count++;
        return virtualElements.get(count - 1);
    }
}
