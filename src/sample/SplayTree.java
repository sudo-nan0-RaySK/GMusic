package sample;

import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**Splay Class Node **/
class SplayNode implements Serializable
{
    SplayNode left, right, parent;
    int element;


    public SplayNode()
    {
        this(0, null, null, null);
    }

    public SplayNode(int ele)
    {
        this(ele, null, null, null);
    }

    public SplayNode(int ele, SplayNode left, SplayNode right, SplayNode parent)
    {
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.element = ele;
    }
}

/** Class SplayTree **/
 class SplayTree implements Serializable
{
    public SplayNode root;
    private int count = 0;


    public SplayTree()
    {
        root = null;
    }


    public boolean isEmpty()
    {
        return root == null;
    }


    public void clear()
    {
        root = null;
    }


    public void insert(int ele)
    {
        SplayNode z = root;
        SplayNode p = null;
        while (z != null)
        {
            p = z;
            if (ele < p.element)
                z = z.right;
            else
                z = z.left;
        }
        z = new SplayNode();
        z.element = ele;
        z.parent = p;
        if (p == null)
            root = z;
        else if (ele < p.element)
            p.right = z;
        else
            p.left = z;
        Splay(z);
        count++;
    }

    public void makeLeftChildParent(SplayNode c, SplayNode p)
    {
        if ((c == null) || (p == null) || (p.left != c) || (c.parent != p))
            throw new RuntimeException("WRONG");

        if (p.parent != null)
        {
            if (p == p.parent.left)
                p.parent.left = c;
            else
                p.parent.right = c;
        }
        if (c.right != null)
            c.right.parent = p;

        c.parent = p.parent;
        p.parent = c;
        p.left = c.right;
        c.right = p;
    }


    public void makeRightChildParent(SplayNode c, SplayNode p)
    {
        if ((c == null) || (p == null) || (p.right != c) || (c.parent != p))
            throw new RuntimeException("WRONG");
        if (p.parent != null)
        {
            if (p == p.parent.left)
                p.parent.left = c;
            else
                p.parent.right = c;
        }
        if (c.left != null)
            c.left.parent = p;
        c.parent = p.parent;
        p.parent = c;
        p.right = c.left;
        c.left = p;
    }

    // Splaying Function- Core Algorithm
    private void Splay(SplayNode x)
    {
        while (x.parent != null)
        {
            SplayNode Parent = x.parent;
            SplayNode GrandParent = Parent.parent;
            if (GrandParent == null)
            {
                if (x == Parent.left)
                    makeLeftChildParent(x, Parent);
                else
                    makeRightChildParent(x, Parent);
            }
            else
            {
                if (x == Parent.left)
                {
                    if (Parent == GrandParent.left)
                    {
                        makeLeftChildParent(Parent, GrandParent);
                        makeLeftChildParent(x, Parent);
                    }
                    else
                    {
                        makeLeftChildParent(x, x.parent);
                        makeRightChildParent(x, x.parent);
                    }
                }
                else
                {
                    if (Parent == GrandParent.left)
                    {
                        makeRightChildParent(x, x.parent);
                        makeLeftChildParent(x, x.parent);
                    }
                    else
                    {
                        makeRightChildParent(Parent, GrandParent);
                        makeRightChildParent(x, Parent);
                    }
                }
            }
        }
        root = x;
    }



    public int countNodes()
    {
        return count;
    }


    public SplayNode search(int val)
    {
        return findNode(val) ;
    }

    public boolean contains(int element)
    {
        if(this.findNode(element)==null)
        {
            return false;
        }
        return true;
    }
    private SplayNode findNode(int ele)
    {
        SplayNode z = root;
        while (z != null)
        {
            if (ele < z.element)
                z = z.right;
            else if (ele > z.element)
                z = z.left;
            else
            {
                Splay(z);
                return z;
            }

        }
        Splay(z);
        return null;
    }


    public void inorder()
    {
        inorder(root);
    }
    private void inorder(SplayNode r)
    {
        if (r != null)
        {
            inorder(r.left);
            System.out.print(r.element +" ");
            inorder(r.right);
        }
    }


    public void preorder()
    {
        preorder(root);
    }
    private void preorder(SplayNode r)
    {
        if (r != null)
        {
            System.out.print(r.element +" ");
            preorder(r.left);
            preorder(r.right);
        }
    }


    public void postorder()
    {
        postorder(root);
    }
    private void postorder(SplayNode r)
    {
        if (r != null)
        {
            postorder(r.left);
            postorder(r.right);
            System.out.print(r.element +" ");
        }
    }

    public SplayNode getRoot() {
        return root;
    }
}

