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

    public void newNode(int value){
        if(rootNode == null){
            rootNode = new Node(value, Color.BLACK);
        }
        else {
            Node children = new Node(value, Color.RED);
            findPositionForNewNode(children, rootNode, false);
        }
    }

    private boolean findPositionForNewNode(Node children, Node parent, boolean positionFound) {

        while(!positionFound){

            if(children.getValue() < parent.getValue()){

                if(parent.getLeftNode() == null){

                    children.setParent(parent);
                    parent.setLeftNode(children);

                    if(!parent.isBalanced()){

                    }

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

                    if(!parent.isBalanced()){
                        rotateLeft(parent, children, parent.getParent());
                    }

                    positionFound = true;
                }
                else {
                    return findPositionForNewNode(children, parent.getRightNode(), positionFound);
                }
            }
        }
        return positionFound;
    }

    private void rotateLeft(Node parent, Node children, Node grandParent) {
        if(grandParent != null){
            grandParent.setLeftNode(children);
            children.setLeftNode(parent);
        }
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
