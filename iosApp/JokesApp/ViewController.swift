//
//  ViewController.swift
//  mppexample
//
//  Created by Kurt on 20/08/2019.
//  Copyright © 2019 Kurt. All rights reserved.
//

import UIKit
import JokesShared

class ViewController: UITableViewController {
  // MARK: Properties
  var jokes = [Joke]()
  var visibleJokes = Set<Int>()

  let viewModel = JokesViewModel.Companion.init().create()
  var closeables = [Ktor_ioCloseable]()

  deinit {
    closeables.forEach { (closeable) in
      closeable.close()
    }
  }

  override func viewDidLoad() {
    super.viewDidLoad()

    closeables.append(
      viewModel.jokes.watch { jokesNullable in
        if let jokes = jokesNullable {
          self.jokes += jokes as! Array<Joke>
          self.tableView.reloadData()
        }
      }
    )


    closeables.append(
      viewModel.jokesState.watch { jokesStateNullable in
        if let jokesState = jokesStateNullable {
          jokesState
        }
      }
    )
  }

  override func numberOfSections(in tableView: UITableView) -> Int {
    return 1
  }

  override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
    return jokes.count
  }

  override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
    guard let cell = tableView.dequeueReusableCell(withIdentifier: "JokesTableViewCell", for: indexPath) as? JokesTableViewCell else {
      fatalError("The dequeued cell is not an instance of JokesTableViewCell.")
    }

    let joke = jokes[indexPath.row]

    cell.txtSetup.text = joke.setup
    cell.txtPunchline.text = joke.punchline
    cell.txtPunchline.isHidden = !visibleJokes.contains(indexPath.row)

    return cell
  }

  override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
    guard let cell = tableView.cellForRow(at: indexPath) as? JokesTableViewCell else {
      fatalError("The cell is not an instance of JokesTableViewCell.")
    }
    if (visibleJokes.contains(indexPath.row)) {
      visibleJokes.remove(indexPath.row)
    } else {
      visibleJokes.insert(indexPath.row)
    }
    cell.txtPunchline.isHidden = !cell.txtPunchline.isHidden
  }
}
