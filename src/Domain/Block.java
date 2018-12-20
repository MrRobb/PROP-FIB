package Domain;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.function.Function;

class Block implements Function<In,Out>{

    private Function<In, Out> function;
    private ArrayList<Pair<String, java.lang.Class>> args;

    Block(Function<In, Out> function, ArrayList<Pair<String, java.lang.Class>> args) {
        this.function = function;
        this.args = args;
    }

    ArrayList<Pair<String, java.lang.Class>> getArgs() {
        return args;
    }

    String toStringArgs() {
        StringBuilder s = new StringBuilder();
        if (args != null) {
            int i = 0;
            for ( Pair<String, java.lang.Class> arg : args) {
                if (i != 0) {
                    s.append(", ");
                }
                s.append(toStringArg(i));
                i++;
            }
        }
        return s.toString();
    }

    String toStringArg(int i) {
        StringBuilder s = new StringBuilder();
        if (args != null) {
            Pair<String, java.lang.Class> arg = args.get(i);
            if (arg != null) {
                s.append(arg.getKey());
                s.append(" ");
                s.append("(");
                s.append(arg.getValue().getSimpleName());
                s.append(")");
            }
        }
        return s.toString();
    }

    int argSize() {
        return args.size();
    }


    @Override
    public Out apply(In in) {
        return function.apply(in);
    }

    @Override
    public <V> Function<V, Out> compose(Function<? super V, ? extends In> before) {
        return null;
    }

    @Override
    public <V> Function<In, V> andThen(Function<? super Out, ? extends V> after) {
        return null;
    }



}
