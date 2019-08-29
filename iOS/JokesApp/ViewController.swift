//
//  ViewController.swift
//  mppexample
//
//  Created by Kurt on 20/08/2019.
//  Copyright © 2019 Kurt. All rights reserved.
//

import UIKit
import Common

class ViewController: UITableViewController, JokesView {
    func showJokes(jokes: [Joke]) {
        self.jokes += jokes
        tableView.reloadData()
    }
    
    var jokes = [Joke]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        let presenter = JokesPresenter(jokesView: self, getJokes: ServiceLocator.init().getJokes)
        
        presenter.getJokes()
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
        
        return cell
    }

}

