import java.util.*;
public class feedforward
{
    //int size;
    Network hidden[];double weightedsum[][],bias[][],weights[][];
    feedforward(Network main1[])
    {
        //size=n;
        hidden=main1;
    }

    double sigmoid(double n)
    {
        return(1.0/(1+Math.exp(-n)));
    }

    void inputs(double ary[])
    {
        Scanner in=new Scanner(System.in);
        hidden[0].weightsum=ary;
        /*for(int a=0;a<size;a++)
        {
            System.out.println("input:"+(a+1));
            
        }*/
    }

    void toArray(int a)
    {
        bias=new double[1][hidden[a+1].weightsum.length];
        for(int a1=0;a1<hidden[a+1].weightsum.length;a1++)
            bias[0][a1]=hidden[a+1].link[a1].bias;
        weights=new double[hidden[a].weightsum.length][hidden[a+1].weightsum.length];
        for(int a1=0;a1<hidden[a].weightsum.length;a1++)
            for(int a2=0;a2<hidden[a+1].weightsum.length;a2++)
                weights[a1][a2]=hidden[a].link[a1].weight[a2];
    }

    double[][] calculate(double inputs[])
    {
        double in[][]=new double[1][inputs.length];
        for(int a=0;a<inputs.length;a++)
            in[0][a]=inputs[a];
        Matrix A=new Matrix(bias);
        Matrix C=new Matrix(in);
        Matrix B=new Matrix(weights);
        Matrix D=A.plus(C.times(B));
        return D.data;
    }

    void main()
    {
        for(int a=0;a<hidden.length-1;a++)
        {
            toArray(a);
            weightedsum=calculate(hidden[a].weightsum);
            for(int a1=0;a1<hidden[a+1].weightsum.length;a1++)
            {
                hidden[a+1].unactivated[a1]=weightedsum[0][a1];
                hidden[a+1].weightsum[a1]=sigmoid(weightedsum[0][a1]);
                }
        }
        //for(int a1=0;a1<3;a1++)
        //System.out.println(hidden[hidden.length-1].weightsum[a1]);
    }
}
