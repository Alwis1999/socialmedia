import React, { useEffect, useState } from "react";
import axios from "axios";
import "../styles/UserPosts.css";

interface Comment {
  id: string | null;
  postId: string | null;
  comment: string;
  user: string;
  commentAt: number[];
}

interface Post {
  id: string;
  content: string;
  user: string;
  createdAt: number[];
  comments: Comment[] | null;
}

const UserPosts: React.FC = () => {
  const [posts, setPosts] = useState<Post[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  const formatDate = (dateArray: number[]) => {
    const date = new Date(
      dateArray[0],
      dateArray[1] - 1,
      dateArray[2],
      dateArray[3],
      dateArray[4],
      dateArray[5]
    );
    return date.toLocaleString("en-US", {
      year: "numeric",
      month: "short",
      day: "numeric",
      hour: "2-digit",
      minute: "2-digit",
    });
  };

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const token = localStorage.getItem("token");
        const response = await axios.get(
          "http://localhost:8080/api/posts/myposts",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        setPosts(response.data);
      } catch (err) {
        setError("Failed to fetch posts. Please try again later.");
      } finally {
        setLoading(false);
      }
    };

    fetchPosts();
  }, []);

  if (loading) {
    return (
      <div className="posts-loading">
        <div className="loading-spinner"></div>
        <p>Loading your posts...</p>
      </div>
    );
  }

  if (error) {
    return <div className="posts-error">{error}</div>;
  }

  return (
    <div className="posts-container">
      <h2>Your Posts</h2>
      {posts.length === 0 ? (
        <div className="no-posts">
          <p>You haven't created any posts yet.</p>
          <button className="create-post-btn">Create Your First Post</button>
        </div>
      ) : (
        <div className="posts-list">
          {posts.map((post) => (
            <div key={post.id} className="post-card">
              <div className="post-header">
                <div className="user-info">
                  <div className="user-avatar">
                    {post.user[0].toUpperCase()}
                  </div>
                  <span className="username">{post.user}</span>
                </div>
                <span className="post-date">{formatDate(post.createdAt)}</span>
              </div>
              <div className="post-content">{post.content}</div>
              <div className="post-comments">
                {post.comments && post.comments.length > 0 && (
                  <>
                    <h4>Comments ({post.comments.length})</h4>
                    <div className="comments-list">
                      {post.comments.map((comment, index) => (
                        <div key={index} className="comment">
                          <div className="comment-header">
                            <div className="comment-user">
                              <div className="user-avatar small">
                                {comment.user[0].toUpperCase()}
                              </div>
                              <span className="username">{comment.user}</span>
                            </div>
                            <span className="comment-date">
                              {formatDate(comment.commentAt)}
                            </span>
                          </div>
                          <div className="comment-content">
                            {comment.comment}
                          </div>
                        </div>
                      ))}
                    </div>
                  </>
                )}
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default UserPosts;
