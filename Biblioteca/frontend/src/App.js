import { useEffect, useState } from "react";

function App() {
    const [books, setBooks] = useState([]);
    const [selectedBook, setSelectedBook] = useState(null);
    const [avaliacoes, setAvaliacoes] = useState([]);
    const [nota, setNota] = useState(5);
    const [comentario, setComentario] = useState("");
    const [userId, setUserId] = useState(1);

    useEffect(() => {
        fetch("http://localhost:8080/book")
            .then((res) => res.json())
            .then((data) => setBooks(data));
    }, []);

    const selecionarLivro = (book) => {
        setSelectedBook(book);
        fetch(`http://localhost:8081/avaliacao/livro/${book.id}`)
            .then((res) => res.json())
            .then((data) => setAvaliacoes(data));
    };

    const enviarAvaliacao = () => {
        fetch("http://localhost:8081/avaliacao", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                bookId: selectedBook.id,
                userId: userId,
                nota: nota,
                comentario: comentario,
            }),
        })
            .then((res) => res.json())
            .then(() => {
                setComentario("");
                selecionarLivro(selectedBook);
            });
    };

    return (
        <div style={{ padding: "20px", fontFamily: "Arial" }}>
            <h1>📚 Biblioteca</h1>

            <div style={{ display: "flex", gap: "40px" }}>
                {/* Lista de livros */}
                <div style={{ flex: 1 }}>
                    <h2>Livros</h2>
                    <ul style={{ listStyle: "none", padding: 0 }}>
                        {books.map((book) => (
                            <li
                                key={book.id}
                                onClick={() => selecionarLivro(book)}
                                style={{
                                    padding: "10px",
                                    marginBottom: "8px",
                                    border: "1px solid #ccc",
                                    borderRadius: "6px",
                                    cursor: "pointer",
                                    background: selectedBook?.id === book.id ? "#e0f0ff" : "white",
                                }}
                            >
                                <strong>{book.details.titulo}</strong>
                                <br />
                                <small>{book.details.autor}</small>
                            </li>
                        ))}
                    </ul>
                </div>

                {/* Avaliações */}
                {selectedBook && (
                    <div style={{ flex: 2 }}>
                        <h2>Avaliações — {selectedBook.details.titulo}</h2>

                        {avaliacoes.length === 0 ? (
                            <p>Nenhuma avaliação ainda.</p>
                        ) : (
                            <ul style={{ listStyle: "none", padding: 0 }}>
                                {avaliacoes.map((av) => (
                                    <li
                                        key={av.id}
                                        style={{
                                            padding: "10px",
                                            marginBottom: "8px",
                                            border: "1px solid #ddd",
                                            borderRadius: "6px",
                                        }}
                                    >
                                        <strong>⭐ {av.nota}/5</strong> — Usuário {av.userId}
                                        <br />
                                        <span>{av.comentario}</span>
                                    </li>
                                ))}
                            </ul>
                        )}

                        {/* Formulário de avaliação */}
                        <h3>Avaliar este livro</h3>
                        <div style={{ display: "flex", flexDirection: "column", gap: "10px", maxWidth: "400px" }}>
                            <label>
                                ID do Usuário:
                                <input
                                    type="number"
                                    value={userId}
                                    onChange={(e) => setUserId(Number(e.target.value))}
                                    style={{ marginLeft: "10px", width: "60px" }}
                                />
                            </label>
                            <label>
                                Nota (1-5):
                                <input
                                    type="number"
                                    min="1"
                                    max="5"
                                    value={nota}
                                    onChange={(e) => setNota(Number(e.target.value))}
                                    style={{ marginLeft: "10px", width: "60px" }}
                                />
                            </label>
                            <label>
                                Comentário:
                                <textarea
                                    value={comentario}
                                    onChange={(e) => setComentario(e.target.value)}
                                    rows="3"
                                    style={{ display: "block", width: "100%", marginTop: "4px" }}
                                />
                            </label>
                            <button
                                onClick={enviarAvaliacao}
                                style={{
                                    padding: "10px",
                                    background: "#007bff",
                                    color: "white",
                                    border: "none",
                                    borderRadius: "6px",
                                    cursor: "pointer",
                                }}
                            >
                                Enviar Avaliação
                            </button>
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
}

export default App;