import Ynu.Sei.cpLibrary.BASIC.cpDraw;
import Ynu.Sei.cpLibrary.cellgrid2D.Point2D;

import java.awt.*;
import java.util.*;


public class pushBox extends Point2DMatrix {
    private HashMap<Point2D, Integer> Map = new HashMap<Point2D, Integer>();
    private Point2D Goal;
    static final int road = 0;
    static final int wall = 1;
    static final int gamer = 2;
    static final int box = 3;
    static final int goal = 4;


    public pushBox(int M, int N, String Number) throws RuntimeException {
        super(new Point2D(-M / 2, N / 2), M, N, M, N);

        String[] Numbers = Number.split("\\D+");
        if (Numbers.length != M * N) {
            throw new RuntimeException("输入数字个数与行数不符");
        }

        for (int i = 0; i < CenterPointSet.size(); i++) {
            Map.put(CenterPointSet.get(i), Integer.valueOf(Numbers[i]));
        }
        this.Goal = getPoint(goal);
    }

    public pushBox(HashMap<Point2D, Integer> Map, Point2D Goal) {
        this.Map = Map;
        this.Goal = Goal;
    }

    public void draw() {
        cpDraw.setX(-M / 2, M / 2, 1);
        cpDraw.setY(-N / 2, N / 2, 1);

        for (int i = 0; i < CenterPointSet.size(); i++) {
            cpDraw.setPenColor(Color.BLACK);
            switch (Map.get(CenterPointSet.get(i))) {
                case road://空白
                    cpDraw.Rectangle(CenterPointSet.get(i).x(), CenterPointSet.get(i).y(), 1, 1);
                    break;
                case wall://墙壁
                    cpDraw.FilledSquare(CenterPointSet.get(i).x(), CenterPointSet.get(i).y(), 0.5);
                    break;
                case gamer://人
                    cpDraw.FilledCircle(CenterPointSet.get(i).x(), CenterPointSet.get(i).y(), 0.5);
                    break;
                case box://箱子
                    cpDraw.setPenColor(Color.CYAN);
                    cpDraw.FilledSquare(CenterPointSet.get(i).x(), CenterPointSet.get(i).y(), 0.5);
                    break;
                case goal://目标
                    cpDraw.setPenColor(Color.ORANGE);
                    cpDraw.FilledSquare(CenterPointSet.get(i).x(), CenterPointSet.get(i).y(), 0.5);
                    break;

            }
        }

    }

    public <K, V> K getKey(Map<K, V> map, V value) {
        for (java.util.Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Point2D getPoint(int value) {
        return getKey(Map, value);

    }

    public void UP() {
        double X = getPoint(gamer).x();
        double Y = getPoint(gamer).y();

        if (Y < Height / 2 - 0.5 &&
                (Map.get(new Point2D(X, Y + 1)) == road || Map.get(new Point2D(X, Y + 1)) == goal)) {
            Map.put(new Point2D(X, Y), road);
            Map.put(new Point2D(X, Y + 1), gamer);
        }
        if (Y < Height / 2 - 1.5 && Map.get(new Point2D(X, Y + 1)) == box &&
                (Map.get(new Point2D(X, Y + 2)) == road || Map.get(new Point2D(X, Y + 1)) == box &&
                        Map.get(new Point2D(X, Y + 2)) == goal)) {
            Map.put(new Point2D(X, Y), road);
            Map.put(new Point2D(X, Y + 1), gamer);
            Map.put(new Point2D(X, Y + 2), box);
        }
    }

    public void DOWN() {
        double X = getPoint(gamer).x();
        double Y = getPoint(gamer).y();

        if (Y > -Height / 2 + 0.5 &&
                (Map.get(new Point2D(X, Y - 1)) == road || Map.get(new Point2D(X, Y - 1)) == goal)) {
            Map.put(new Point2D(X, Y), road);
            Map.put(new Point2D(X, Y - 1), gamer);
        }
        if (Y > -Height / 2 + 1.5 &&
                (Map.get(new Point2D(X, Y - 1)) == box && Map.get(new Point2D(X, Y - 2)) == road || Map.get(new Point2D(X, Y - 1)) == box && Map.get(new Point2D(X, Y - 2)) == goal)) {
            Map.put(new Point2D(X, Y), road);
            Map.put(new Point2D(X, Y - 1), gamer);
            Map.put(new Point2D(X, Y - 2), box);
        }
    }

    public void LEFT() {
        double X = getPoint(gamer).x();
        double Y = getPoint(gamer).y();

        if (X > -Width / 2 + 0.5 &&
                (Map.get(new Point2D(X - 1, Y)) == road || Map.get(new Point2D(X - 1, Y)) == goal)) {
            Map.put(new Point2D(X, Y), road);
            Map.put(new Point2D(X - 1, Y), gamer);
        }
        if (X > -Width / 2 + 1.5 &&
                (Map.get(new Point2D(X - 1, Y)) == box && Map.get(new Point2D(X - 2, Y)) == road || Map.get(new Point2D(X - 1, Y)) == box && Map.get(new Point2D(X - 2, Y)) == goal)) {
            Map.put(new Point2D(X, Y), road);
            Map.put(new Point2D(X - 1, Y), gamer);
            Map.put(new Point2D(X - 2, Y), box);
        }
    }

    public void RIGHT() {
        double X = getPoint(gamer).x();
        double Y = getPoint(gamer).y();

        if (X < Height / 2 - 0.5 &&
                (Map.get(new Point2D(X + 1, Y)) == road || Map.get(new Point2D(X + 1, Y)) == goal)) {
            Map.put(new Point2D(X, Y), road);
            Map.put(new Point2D(X + 1, Y), gamer);
        }
        if (X < Height / 2 - 1.5 &&
                Map.get(new Point2D(X + 1, Y)) == box && Map.get(new Point2D(X + 2, Y)) == road || Map.get(new Point2D(X + 1, Y)) == box && Map.get(new Point2D(X + 2, Y)) == goal) {
            Map.put(new Point2D(X, Y), road);
            Map.put(new Point2D(X + 1, Y), gamer);
            Map.put(new Point2D(X + 2, Y), box);
        }
    }

    public void MOVE(char type) {
        switch (type) {
            case 'w':
                UP();
                break;
            case 's':
                DOWN();
                break;
            case 'd':
                RIGHT();
                break;
            case 'a':
                LEFT();
                break;
            default:
                break;
        }
        if (Map.get(Goal) == road) {
            Map.put(Goal, goal);
        }
    }

    public boolean isWin() {
        return Map.get(Goal) == box;
    }

    public void Game() {
        while (true) {
            if (cpDraw.hasNextKeyTyped()) {
                char c = cpDraw.nextKeyTyped();
                MOVE(c);
                cpDraw.clear();
                draw();
                if (isWin()) {
                    System.out.println("Win");
                    break;
                }
            }

        }

    }

    private class boxState {
        Point2D nowGamer;
        Point2D nextGamer;
        Point2D nowBox;
        Point2D nextBox;
        int step;
        int Manhattan;
        int Cost;
        String preDirection = " ";
        boxState Pre;

        public boxState(Point2D nowGamer, Point2D nextGamer, Point2D nowBox, Point2D nextBox, int step) {
            this.nowGamer = nowGamer;
            this.nextGamer = nextGamer;
            this.nowBox = nowBox;
            this.nextBox = nextBox;
            this.step = step;
            this.Manhattan = Manhattan(nextBox, Goal);
            this.Cost = step + Manhattan;
        }

        public void setPreDirection(String preDirection) {
            this.preDirection = preDirection;
        }

        public void setPre(boxState pre) {
            Pre = pre;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            boxState boxState = (boxState) o;
            return Objects.equals(nowBox, boxState.nowBox) &&
                    Objects.equals(nextBox, boxState.nextBox);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nowBox, nextBox);
        }
    }

    private class gamerState {
        Point2D NowGamer;
        Point2D NextGamer;
        int step;
        int Manhattan;
        int Cost;
        char preDirection;
        gamerState Pre;

        public gamerState(Point2D nowGamer, Point2D nextGamer, int step, Point2D end, char preDirection) {
            this.NowGamer = nowGamer;
            this.NextGamer = nextGamer;
            this.step = step;
            this.Manhattan = Manhattan(nextGamer, end);
            this.Cost = step + Manhattan;
            this.preDirection = preDirection;
        }

        public void setPre(gamerState pre) {
            this.Pre = pre;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            gamerState that = (gamerState) o;
            return preDirection == that.preDirection &&
                    Objects.equals(NextGamer, that.NextGamer);
        }

        @Override
        public int hashCode() {
            return Objects.hash(NextGamer, preDirection);
        }
    }

    public int Manhattan(Point2D a, Point2D b) {
        return (int) (Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y()));
    }

    public boolean PointEqual(Point2D a, Point2D b) {
        return a.x() == b.x() && a.y() == b.y();
    }

    public void boxNeighbor(MinPQ<boxState> StateMinPQ, HashSet<boxState> closeSet, Point2D nowGamer, Point2D nowBox, int step) {
        if (Map.get(new Point2D(nowBox.x() - 1, nowBox.y())) != wall && Map.get(new Point2D(nowBox.x() + 1, nowBox.y())) != wall) {
            boxState left = new boxState(nowGamer, new Point2D(nowBox.x() + 1, nowBox.y()), nowBox, new Point2D(nowBox.x() - 1, nowBox.y()), step);
            left.setPreDirection("a");
            boxState right = new boxState(nowGamer, new Point2D(nowBox.x() - 1, nowBox.y()), nowBox, new Point2D(nowBox.x() + 1, nowBox.y()), step);
            right.setPreDirection("d");
            if (!closeSet.contains(left)) {
                StateMinPQ.insert(left);
            }
            if (!closeSet.contains(right)) {
                StateMinPQ.insert(right);
            }
        }
        if (Map.get(new Point2D(nowBox.x(), nowBox.y() + 1)) != wall && Map.get(new Point2D(nowBox.x(), nowBox.y() - 1)) != wall) {
            boxState up = new boxState(nowGamer, new Point2D(nowBox.x(), nowBox.y() - 1), nowBox, new Point2D(nowBox.x(), nowBox.y() + 1), step);
            up.setPreDirection("w");
            boxState down = new boxState(nowGamer, new Point2D(nowBox.x(), nowBox.y() + 1), nowBox, new Point2D(nowBox.x(), nowBox.y() - 1), step);
            down.setPreDirection("s");
            if (!closeSet.contains(up)) {
                StateMinPQ.insert(up);
            }
            if (!closeSet.contains(down)) {
                StateMinPQ.insert(down);
            }
        }
    }

    public void boxNeighbor(MinPQ<boxState> StateMinPQ, HashSet<boxState> closeSet, Point2D nowGamer, Point2D nowBox, int step, boxState pre) {
        if (nowBox.x() > -Width / 2 + 0.5 && nowBox.x() < Width / 2 - 0.5 &&
                Map.get(new Point2D(nowBox.x() - 1, nowBox.y())) != wall && Map.get(new Point2D(nowBox.x() + 1, nowBox.y())) != wall) {
            boxState left = new boxState(nowGamer, new Point2D(nowBox.x() + 1, nowBox.y()), nowBox, new Point2D(nowBox.x() - 1, nowBox.y()), step);
            left.setPre(pre);
            left.setPreDirection("a");
            boxState right = new boxState(nowGamer, new Point2D(nowBox.x() - 1, nowBox.y()), nowBox, new Point2D(nowBox.x() + 1, nowBox.y()), step);
            right.setPre(pre);
            right.setPreDirection("d");
            if (!closeSet.contains(left)) {
                StateMinPQ.insert(left);
            }
            if (!closeSet.contains(right)) {
                StateMinPQ.insert(right);
            }
        }
        if (nowBox.y() > -Height / 2 + 0.5 && nowBox.y() < Height / 2 - 0.5 &&
                Map.get(new Point2D(nowBox.x(), nowBox.y() + 1)) != wall && Map.get(new Point2D(nowBox.x(), nowBox.y() - 1)) != wall) {
            boxState up = new boxState(nowGamer, new Point2D(nowBox.x(), nowBox.y() - 1), nowBox, new Point2D(nowBox.x(), nowBox.y() + 1), step);
            up.setPre(pre);
            up.setPreDirection("w");
            boxState down = new boxState(nowGamer, new Point2D(nowBox.x(), nowBox.y() + 1), nowBox, new Point2D(nowBox.x(), nowBox.y() - 1), step);
            down.setPre(pre);
            down.setPreDirection("s");
            if (!closeSet.contains(up)) {
                StateMinPQ.insert(up);
            }
            if (!closeSet.contains(down)) {
                StateMinPQ.insert(down);
            }
        }
    }

    public void gamerNeighbor(MinPQ<gamerState> stateMinPQ, HashSet<gamerState> gamerSet, Point2D nowGamer, Point2D end, Point2D box,int step) {
        if (nowGamer.x() > -Width / 2 + 0.5 &&
                Map.get(new Point2D(nowGamer.x() - 1, nowGamer.y())) != wall &&
                !PointEqual(new Point2D(nowGamer.x() - 1, nowGamer.y()),box)
        ) {
            gamerState left = new gamerState(nowGamer, new Point2D(nowGamer.x() - 1, nowGamer.y()), step, end, 'a');
            if (!gamerSet.contains(left)) {
                stateMinPQ.insert(left);
            }
        }
        if (nowGamer.x() < Width / 2 - 0.5 &&
                Map.get(new Point2D(nowGamer.x() + 1, nowGamer.y())) != wall &&
                !PointEqual(new Point2D(nowGamer.x() + 1, nowGamer.y()),box)
        ) {
            gamerState right = new gamerState(nowGamer, new Point2D(nowGamer.x() + 1, nowGamer.y()), step, end, 'd');
            if (!gamerSet.contains(right)) {
                stateMinPQ.insert(right);
            }
        }
        if (nowGamer.y() < Height / 2 - 0.5 &&
                Map.get(new Point2D(nowGamer.x(), nowGamer.y() + 1)) != wall &&
                !PointEqual(new Point2D(nowGamer.x() , nowGamer.y()+1),box)
        ) {
            gamerState up = new gamerState(nowGamer, new Point2D(nowGamer.x(), nowGamer.y() + 1), step, end, 'w');
            if (!gamerSet.contains(up)) {
                stateMinPQ.insert(up);
            }
        }
        if (nowGamer.y() > -Height / 2 + 0.5 &&
                Map.get(new Point2D(nowGamer.x(), nowGamer.y() - 1)) != wall &&
                !PointEqual(new Point2D(nowGamer.x(), nowGamer.y()-1),box)
        ) {
            gamerState down = new gamerState(nowGamer, new Point2D(nowGamer.x(), nowGamer.y() - 1), step, end, 's');
            if (!gamerSet.contains(down)) {
                stateMinPQ.insert(down);
            }
        }


    }

    public void gamerNeighbor(MinPQ<gamerState> stateMinPQ, HashSet<gamerState> gamerSet, Point2D nowGamer, Point2D end,Point2D box, int step, gamerState pre) {
        if (nowGamer.x() > -Width / 2 + 0.5 &&
                Map.get(new Point2D(nowGamer.x() - 1, nowGamer.y())) != wall &&
                !PointEqual(new Point2D(nowGamer.x()-1, nowGamer.y()),box)) {
            gamerState left = new gamerState(nowGamer, new Point2D(nowGamer.x() - 1, nowGamer.y()), step, end, 'a');
            left.setPre(pre);
            if (!gamerSet.contains(left)) {
                stateMinPQ.insert(left);
            }
        }
        if (nowGamer.x() < Width / 2 - 0.5 &&
                Map.get(new Point2D(nowGamer.x() + 1, nowGamer.y())) != wall &&
                !PointEqual(new Point2D(nowGamer.x()+1, nowGamer.y()),box)) {
            gamerState right = new gamerState(nowGamer, new Point2D(nowGamer.x() + 1, nowGamer.y()), step, end, 'd');
            right.setPre(pre);
            if (!gamerSet.contains(right)) {
                stateMinPQ.insert(right);
            }
        }
        if (nowGamer.y() < Height / 2 - 0.5 &&
                Map.get(new Point2D(nowGamer.x(), nowGamer.y() + 1)) != wall &&
                !PointEqual(new Point2D(nowGamer.x(), nowGamer.y()+1),box)) {
            gamerState up = new gamerState(nowGamer, new Point2D(nowGamer.x(), nowGamer.y() + 1), step, end, 'w');
            up.setPre(pre);
            if (!gamerSet.contains(up)) {
                stateMinPQ.insert(up);
            }
        }
        if (nowGamer.y() > -Height / 2 + 0.5 &&
                Map.get(new Point2D(nowGamer.x(), nowGamer.y() - 1)) != wall &&
                !PointEqual(new Point2D(nowGamer.x(), nowGamer.y()-1),box)) {
            gamerState down = new gamerState(nowGamer, new Point2D(nowGamer.x(), nowGamer.y() - 1), step, end, 's');
            down.setPre(pre);
            if (!gamerSet.contains(down)) {
                stateMinPQ.insert(down);
            }
        }

    }

    public boolean Reachable(boxState boxState) {
        HashSet<gamerState> gamerSet = new HashSet<>();
        MinPQ<gamerState> stateMinPQ = new MinPQ<>(new Comparator<gamerState>() {
            @Override
            public int compare(gamerState o1, gamerState o2) {
                return o1.Cost - o2.Cost;
            }
        });

        if (PointEqual(boxState.nowGamer, boxState.nextGamer)) {
            return true;
        } else {
            gamerNeighbor(stateMinPQ, gamerSet, boxState.nowGamer, boxState.nextGamer, boxState.nowBox,1);

            while (!stateMinPQ.isEmpty() && !PointEqual(stateMinPQ.min().NextGamer, boxState.nextGamer)) {
                gamerState Min = stateMinPQ.delMin();
                gamerSet.add(Min);
                gamerNeighbor(stateMinPQ, gamerSet, Min.NextGamer, boxState.nextGamer, boxState.nowBox,Min.step + 1, Min);
            }
            if (stateMinPQ.isEmpty()) {
                return false;
            } else {
                gamerState Min = stateMinPQ.delMin();
                StringBuilder path = new StringBuilder();
                for(int i=Min.step;i>0;i--){
                    path.append(Min.preDirection);
                    Min = Min.Pre;
                }
                boxState.setPreDirection(path.reverse().toString()+boxState.preDirection);
                return true;
            }
        }
    }

    public void autoWin(){
        HashSet<boxState> closeSet = new HashSet<>();
        MinPQ<boxState> StateMinPQ = new MinPQ<>(new Comparator<boxState>() {
            @Override
            public int compare(boxState o1, boxState o2) {
                return o1.Cost-o2.Cost;
            }
        });
        if(isWin()){
            System.out.println("WIN");
        }else {
            boxNeighbor(StateMinPQ,closeSet,getPoint(gamer),getPoint(box),1);
            while (!StateMinPQ.isEmpty() && !PointEqual(StateMinPQ.min().nextBox,Goal)){
                boxState Min = StateMinPQ.delMin();
                if(Reachable(Min)){
                    closeSet.add(Min);
                    boxNeighbor(StateMinPQ,closeSet,Min.nowBox,Min.nextBox,Min.step+1,Min);
                }
            }
            if(StateMinPQ.isEmpty()){
                System.out.println("can't Win");
            }else {
                System.out.println("Win");
                boxState Min = StateMinPQ.delMin();
                StringBuilder path = new StringBuilder();
                ArrayList<String> temp = new ArrayList<>();
                for(int i= Min.step;i>0;i--){
                    temp.add(Min.preDirection);
                    Min = Min.Pre;
                }
                for(int i = temp.size()-1;i>=0;i--){
                    path.append(temp.get(i));
                }
                for(int i =0;i<path.length();i++){
                    MOVE(path.charAt(i));
                    cpDraw.clear();
                    draw();
                    cpDraw.show(300);
                }
                }
            }
        }

}









    

