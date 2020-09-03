import Ynu.Sei.cpLibrary.cellgrid2D.Point2D;

import java.util.ArrayList;

public class Point2DMatrix {
    ArrayList<Point2D> CenterPointSet = new ArrayList<Point2D>();
    Point2D UpperLeftPoint;
    double Width;
    double Height;
    int M ;
    int N ;

    public Point2DMatrix() {
        this(new Point2D(0, 0), 1.0, 1.0, 10, 10);
    }

    public Point2DMatrix(Point2D CenterPoint, double Width, double Height, int M) {
        this(new Point2D(CenterPoint.x() - Width / 2, CenterPoint.y() + Height / 2), Width, Height, M, M);
    }

    public Point2DMatrix(Point2D UpperLeftCorner, double Width, double Height, int M, int N) {
        this.UpperLeftPoint = UpperLeftCorner;
        this.Height = Height;
        this.Width = Width;
        this.M = M;
        this.N = N;
        double delta_x = 1;
        double delta_y = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                double x_center = UpperLeftCorner.x() + j * delta_x + delta_x / 2;
                double y_center = UpperLeftCorner.y() - i * delta_y - delta_y / 2;
                Point2D point = new Point2D(x_center, y_center);
                CenterPointSet.add(point);
            }

        }
    }

    public ArrayList<Point2D> points() {
        return (CenterPointSet);
    }

    public Point2D point(int r, int c) {
        return CenterPointSet.get(r * N + c);
    }

}