import java.awt.*;
import java.util.*;
import java.util.stream.IntStream;

public class problem {
    java.awt.Point[] findPlayers(String[] photo,int team,int threshold){
        if (photo.length==0)
            return null;
        char[][] Photo = new char [photo.length][photo[0].length()];
        Point[] ans = new Point[1000];//number isn't accurate
        int Count=0;
        for (int i=0;i < photo.length;i++)
            Photo[i] = photo[i].toCharArray();
        ArrayList<Integer> bounds=new ArrayList();
        ArrayList<Integer> Area = new ArrayList();
        for (int x=0;x<Photo[0].length;x++) {
            for (int y = 0; y < Photo.length; y++) {
                if (Photo[y][x] == (char) (team + 48)) {
                    bounds = Check(y, x, Photo, (char) (team + 48));
                    System.out.println(bounds);
                    int area = 0;
                    for (int i = bounds.get(0); i <= bounds.get(1); i++) {
                        for (int j = bounds.get(2); j <= bounds.get(3); j++) {
                            if (Photo[i][j] == '3') area += 2;
                            Photo[i][j] = 'A';
                        }
                    }
                    if (area>=threshold) {
                        int X = bounds.get(0) + 1 + bounds.get(1), Y = bounds.get(2) + 1 + bounds.get(3);
                        ans[Count] = new Point(X, Y);
                    }
                }
            }
        }
        for (int i=0;i<Count;i++) 
            for (int j=0;j<Count-1-i;j++) {
                if (ans[j].x>ans[j+1].x) {
                    int  temp1 =ans[j].x; int temp2=ans[j].y;
                    ans[j].x=ans[j+1].x; ans[j].y=ans[j+1].y;
                    ans[j+1].x=temp1;   ans[j+1].y=temp2;
                }
                if (ans[j].x==ans[j+1].x) {
                    if (ans[j].y>ans[j+1].y) {
                        int  temp1 =ans[j].x; int temp2=ans[j].y;
                        ans[j].x=ans[j+1].x; ans[j].y=ans[j+1].y;
                        ans[j+1].x=temp1;   ans[j+1].y=temp2;
                    }
                }
            }
        return ans;
    }
    public static ArrayList Check(int initialRow,int initialColumn,char[][] photo,char team){
        Coordinates co=new Coordinates();
        co.State[0]=true;co.State[1]=true;
        int boundC=initialColumn,boundR=initialRow;
        int row=initialRow,column=initialColumn;
        co.Up.add(row);
        while(co.State[0]==true||co.State[1]==true){
            co=ColumnCheck(column,photo,row,team,co);
            if (co.State[1]==true) column+=1;
            co=RowCheck(initialColumn,column,photo,row,team,co);
            if (co.State[0]==true) row++;
        }
        ArrayList<Integer> bounds=new ArrayList();
        co.Up.add(row);
        bounds.add(Collections.min(co.Up));
        bounds.add(row);
        bounds.add(initialColumn);
        bounds.add(column);
        return bounds;
    }
    public static Coordinates RowCheck(int initialColumn,int boundC,char[][] Photo,int row,char team,Coordinates co){
        co.State[0]=false;
        for (int i=initialColumn;i<=boundC;i++)
            if (row!=Photo.length-1&&Photo[row][i]==team&&Photo[row+1][i]==team){
                co.State[0]=true;
                break;
            }
        return co;
    }
    public static Coordinates ColumnCheck (int column,char[][] Photo,int row,char team,Coordinates co){
        co.State[1]=false;
        if (column!=((Photo[0].length)-1)&&Photo[row][column+1]==team&&Photo[row][column]==team) {
            co.State[1] = true;
            if (row!=0) {
                while (row != 0 && Photo[row - 1][column + 1] == team) row--;
                co.Up.add(row);
            }
        }
        if (column!=((Photo[0].length)-1)&&Photo[row][column+1]!=team){
            int i;
            for (i=row;i>Collections.min(co.Up)&&row!=0;i--)
                if(Photo[i][column+1]==team&&Photo[i][column]==team) {
                    co.State[1] = true;
                    break;
                }
            if (co.State[1]==true) {
                while (i != 0 && Photo[i - 1][column + 1] == team) i--;
                co.Up.add(i);
            }
        }
        return co;
    }
    public static int IndexOf(char arr[], char t) {
        return IntStream.range(0, arr.length).filter(i -> t == arr[i]).findFirst().orElse(-1);
    }
    public static ArrayList removeDuplicates(ArrayList list){
        Set set = new LinkedHashSet();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }
}
