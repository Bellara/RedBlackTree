package fh.bocholt.maren.java;


/**
 * 1.) Alle Blattknoten sind schwarz
 * 2.) Ist ein Knoten rot, sind alle Kindsknoten schwarz
 * 3.) Jeder Pfad zu einem Blattknoten enthält die gleiche Anzahl an schwarzen Knoten
 */
public class RedBlackTree {

    private Node rootNode;

    public RedBlackTree() {
    }

    /**
     * Fügt einen neuen Knoten dem Baum hinzu
     * @param value
     */
    public void newNode(int value){
        if(rootNode == null){
            rootNode = new Node(value, Color.BLACK);
        }
        else {
            Node children = new Node(value, Color.RED);
            findPositionForNewNode(children, rootNode, false);
            validateTree(children, children.getParent());
        }
    }

    private void validateTree(Node children, Node parent) {
        if(parent.getColor() == Color.BLACK){
            return;
        }
        else {
            Node uncle = getUncle(children, parent);
            if(uncle != null && uncle.getColor() == Color.RED){
                parent.setColor(Color.BLACK);
                uncle.setColor(Color.BLACK);

                Node grandParent = parent.getParent();
                if(!grandParent.equals(rootNode)){
                    grandParent.setColor(Color.RED);
                }
            }
            else if(uncle == null || uncle.getColor() == Color.BLACK){

                //Kind ist rechts vom Vater, und Vater ist links vom Großvater
                if(isChildrenLeftNode(parent, parent.getParent()) && isChildrenRightNode(children, parent)){
                    rotateLeft(children, parent, parent.getParent());
                }
                //Kind ist links vom Vater, und Vater ist rechts vom Großvater
                else if(isChildrenRightNode(parent, parent.getParent()) && isChildrenLeftNode(children, parent)){
                    rotateRigth(children, parent, parent.getParent());
                }

                //Kind ist links vom Vater, und Vater ist links vom Großvater
                else if(isChildrenLeftNode(parent, parent.getParent()) && isChildrenLeftNode(children, parent)){
                    rotateRigthGrandparent(children, parent, parent.getParent());
                }

                //Kind ist rechts vom Vater, und Vater ist rechts vom Großvater
                else if(isChildrenRightNode(parent, parent.getParent()) && isChildrenRightNode(children, parent)){
                    rotateLeftGrandparent(children, parent, parent.getParent());
                }
            }
        }
    }

    private void rotateLeftGrandparent(Node children, Node parent, Node grandParent) {
        //Parent und Grandparent tauchen die Plätze
        parent.setLeftNode(grandParent);
        grandParent.setRightNode(null);

        //Regel 2
        //Parent muss schwarz gefärbt werden
        parent.setColor(Color.BLACK);
        //Grandparent muss rot gefärbt werden
        grandParent.setColor(Color.RED);
    }

    private void rotateRigthGrandparent(Node children, Node parent, Node grandParent) {
        //Parent und Grandparent tauschen die Plätze
        parent.setRightNode(grandParent);
        grandParent.setLeftNode(null);

        //Regel 2
        //Parent muss schwarz gefärbt werden
        parent.setColor(Color.BLACK);
        //Grandparent muss rot gefärbt werden
        grandParent.setColor(Color.RED);

    }

    private void rotateRigth(Node children, Node parent, Node grandParent) {

        grandParent.setRightNode(children);
        children.setRightNode(parent);
    }

    private void rotateLeft(Node children, Node parent, Node grandParent) {

        grandParent.setLeftNode(children);
        children.setLeftNode(parent);
    }

    private boolean isChildrenLeftNode(Node children, Node parent) {
        return children.equals(parent.getLeftNode());
    }

    private boolean isChildrenRightNode(Node children, Node parent){
        return children.equals(parent.getRightNode());
    }

    private Node getUncle(Node children, Node parent) {
        if(parent.getParent() != null){
            Node grandParent = parent.getParent();

            if(parent.equals(grandParent.getLeftNode())){
                return grandParent.getRightNode();
            }
            else if(parent.equals(grandParent.getRightNode())){
                return grandParent.getLeftNode();
            }
        }
        return null;
    }

    private boolean findPositionForNewNode(Node children, Node parent, boolean positionFound) {

        while(!positionFound){

            if(children.getValue() < parent.getValue()){

                if(parent.getLeftNode() == null){

                    children.setParent(parent);
                    parent.setLeftNode(children);

                    positionFound = true;
                }
                else {
                    return findPositionForNewNode(children, parent.getLeftNode(), positionFound);
                }
            }
            else if(children.getValue() > parent.getValue()){

                if(parent.getRightNode() == null){

                    children.setParent(parent);
                    parent.setRightNode(children);

                    positionFound = true;
                }
                else {
                    return findPositionForNewNode(children, parent.getRightNode(), positionFound);
                }
            }
        }
        return positionFound;
    }


    private int getSizeOfChildrenNodes(Node node, int counter){

        if(node.getLeftNode() != null){
            counter = counter + getSizeOfChildrenNodes(node.getLeftNode(), counter);
        }
        if(node.getRightNode() != null){
            counter = counter + getSizeOfChildrenNodes(node.getRightNode(), counter);
        }

        return counter;
    }

}
