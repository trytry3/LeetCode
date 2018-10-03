/* Thread safe Singleton
*/

class Singleton {
  private static Singleton instance = null;
  private static Object lock = new Object();

  private Singleton() {
  }

  public static synchronized Singleton getInstance() {
    if (instance == null) {
      synchronized (lock) {
        if (instance == null)
          instance = new Singleton();
      }
    }
    return instance;
  }
}