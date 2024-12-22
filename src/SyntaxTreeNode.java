import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class SyntaxTreeNode {
    private String name;
    private List<SyntaxTreeNode> children;

    public SyntaxTreeNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void addChild(SyntaxTreeNode child) {
        if (child != null) {
            children.add(child);
        }
    }

    public String getName() {
        return name;
    }

    public List<SyntaxTreeNode> getChildren() {
        return children;
    }

    // Recursive method to print the tree structure
    public void printTree(String prefix) {
        System.out.println(prefix + name);
        for (SyntaxTreeNode child : children) {
            child.printTree(prefix + "  ");
        }
    }

    // Method to evaluate the tree
    public double evaluate(Map<String, Number> simbolos) {
        switch (name) {
            case "+":
                return children.get(0).evaluate(simbolos) + children.get(1).evaluate(simbolos);
            case "-":
                return children.get(0).evaluate(simbolos) - children.get(1).evaluate(simbolos);
            case "*":
                return children.get(0).evaluate(simbolos) * children.get(1).evaluate(simbolos);
            case "/":
                return children.get(0).evaluate(simbolos) / children.get(1).evaluate(simbolos);
            case ">":
                return children.get(0).evaluate(simbolos) > children.get(1).evaluate(simbolos) ? 1 : 0;
            case "<":
                return children.get(0).evaluate(simbolos) < children.get(1).evaluate(simbolos) ? 1 : 0;
            case "==":
                return children.get(0).evaluate(simbolos) == children.get(1).evaluate(simbolos) ? 1 : 0;
            case "!=":
                return children.get(0).evaluate(simbolos) != children.get(1).evaluate(simbolos) ? 1 : 0;
            case "()":
                return children.get(0).evaluate(simbolos);
            default:
                if (simbolos.containsKey(name)) {
                    return simbolos.get(name).doubleValue();
                }
                return Double.parseDouble(name);
        }
    }

    public void execute(Map<String, Number> simbolos) {
        if (name.equals("Programa")) {
            for (SyntaxTreeNode child : children) {
                child.execute(simbolos);
            }
        } else if (name.equals("Atribuicao")) {
            String varName = children.get(0).getName();
            double value = children.get(1).evaluate(simbolos);
            simbolos.put(varName, value);
        } else if (name.equals("If-Else")) {
            if (children.get(0).evaluate(simbolos) != 0) {
                children.get(1).execute(simbolos);
            } else {
                children.get(2).execute(simbolos);
            }
        } else if (name.equals("While")) {
            while (children.get(0).evaluate(simbolos) != 0) {
                children.get(1).execute(simbolos);
            }
        } else if (name.equals("Return")) {
            System.out.println("Resultado: " + children.get(0).evaluate(simbolos));
        } else if (name.equals("Bloco")) {
            for (SyntaxTreeNode child : children) {
                child.execute(simbolos);
            }
        }
    }
}
