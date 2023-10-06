import java.util.*;
public class Network
{
    double weightsum[],weight[],bias,expected[],error[],unactivated[]; 
    Network hidden[],link[];
    int layers;
    Network(int no)
    {
        layers=no;
        hidden=new Network[layers];
    }

    Network(){}

    void node(int sz)
    {
        weightsum=new double[sz];
        unactivated=new double[sz];
    }

    void weights(int a,int ary[],int c)
    {
        link=new Network[ary[a]];
        for(int a1=0;a1<ary[a];a1++)
            link[a1]=new Network();
        if(a<ary.length-1)
        {
            if(a+1<c)
                for(int a1=0;a1<ary[a];a1++)
                {
                    link[a1].weight=new double[ary[a+1]];
                    if(a>0)
                        link[a1].bias=Math.random()*(2)-1;
                }
            for(int a1=0;a1<ary[a];a1++)
                for(int a2=0;a2<ary[a+1];a2++)
                    link[a1].weight[a2]=Math.random()*(2)-1;
        }
        if(a==ary.length-1)
        {
            for(int a1=0;a1<ary[a];a1++)
                link[a1].bias=Math.random()*(2)-1;
        }
    }

    void network(int ary[])
    {
        for(int a=0;a<ary.length;a++)
        {
            hidden[a]=new Network();
            hidden[a].node(ary[a]);
        }
        for(int a=0;a<ary.length;a++)
            hidden[a].weights(a,ary,layers);
    }
}
