import java.util.*;
public class backprop
{
    Network hidden[];double learningrate; double expects[];
    backprop(Network k[],double learn,double expected[])
    {
        hidden=k;learningrate=learn;expects=expected;
    }

    double dsigmoid(double x)
    {
        return(x*(1-x));
    }

    void expect()
    {
        Scanner in=new Scanner(System.in);
        hidden[hidden.length-1].expected=new double[expects.length];
        hidden[hidden.length-1].expected=expects;
        /*for(int a=0;a<hidden[hidden.length-1].expected.length;a++)
        {
        //System.out.println("expected:"+(a+1));
        hidden[hidden.length-1].expected[a]=expects[a];
        }*/
    }

    void error(int a)
    {
        hidden[a].error=new double[hidden[a].weightsum.length];
        Matrix A=new Matrix(hidden[a].expected,hidden[a].weightsum.length,0);
        Matrix B=new Matrix(hidden[a].weightsum,hidden[a].weightsum.length,0);
        Matrix C=B.minus(A);
        hidden[a].error=C.toSDA().data1;
    }

    void hiddenerror(int a)
    {
        hidden[a].error=new double[hidden[a].weightsum.length];double ary[]=new double[hidden[a+1].weightsum.length];
        for(int a1=0;a1<hidden[a+1].weightsum.length;a1++)
        {
            for(int a2=0;a2<hidden[a].weightsum.length;a2++)
                ary[a1]+=hidden[a].link[a2].weight[a1];
        }
        for(int a1=0;a1<hidden[a].weightsum.length;a1++)
        {
            for(int a2=0;a2<hidden[a+1].weightsum.length;a2++)
                hidden[a].error[a1]+=(hidden[a].link[a1].weight[a2]/(ary[a2]))*hidden[a+1].error[a2];
        }
    }
    //check again
    void changeweights(int a)
    {
        for(int a2=0;a2<hidden[a+1].weightsum.length;a2++)
        {
            for(int a1=0;a1<hidden[a].weightsum.length;a1++)
            {
                //System.out.println(hidden[a+1].error[a2]*dsigmoid(hidden[a+1].weightsum[a2])*hidden[a].weightsum[a1]+" ");
                hidden[a].link[a1].weight[a2]=hidden[a].link[a1].weight[a2]-learningrate*hidden[a+1].error[a2]*dsigmoid(hidden[a+1].weightsum[a2])*hidden[a].weightsum[a1];
            }
        }
        for(int a1=0;a1<hidden[a+1].weightsum.length;a1++)
        {
            //System.out.println(hidden[a+1].error[a1]*dsigmoid(hidden[a+1].weightsum[a1])+" ");
            hidden[a+1].link[a1].bias=hidden[a+1].link[a1].bias-learningrate*hidden[a+1].error[a1]*dsigmoid(hidden[a+1].weightsum[a1]);
        }
        System.out.println();
    }

    void main()
    {
        expect();double cost=0;
        error(hidden.length-1);
        for(int a=hidden.length-2;a>0;a--)
            hiddenerror(a);
        for(int a=hidden.length-2;a>=0;a--)
            changeweights(a);
        for(int a=0;a<hidden[hidden.length-1].error.length;a++)
        {cost+=Math.pow(hidden[hidden.length-1].error[a],2);
            //System.out.print(hidden[hidden.length-1].error[a]+" ");
        }
        cost=cost/hidden[hidden.length-1].error.length;
        System.out.println(cost);
    }
}
