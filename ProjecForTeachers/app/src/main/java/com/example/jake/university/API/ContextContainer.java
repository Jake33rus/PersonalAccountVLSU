package com.example.jake.university.API;

import org.jsoup.nodes.Element;

public class ContextContainer
{
    private Element lSide;
    private Element rSide;


    ContextContainer(Element l, Element r)
    {
        lSide =l;
        rSide = r;;
    }

    public Element get_lSide()
    {
        return lSide;
    }
    public Element get_rSide()
    {
        return rSide;
    }
}
